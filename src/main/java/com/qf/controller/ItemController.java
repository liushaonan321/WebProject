package com.qf.controller;

import com.qf.bean.Item;
import com.qf.service.ItemService;
import com.qf.util.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 刘少楠
 * @Date 2019/05/26
 */

@Controller
@RequestMapping("/item")
public class ItemController {


    @Autowired
    private ItemService itemService;

    @Value("${picTypes}")
    private String picTypes;

    @RequestMapping("/list")
    public String registerUI(@RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "size",defaultValue = "5")Integer size,
                             String name, Model model) {



        PageInfo pageInfo=itemService.findItemByCondition(name,page,size);

        model.addAttribute("pageInfo",pageInfo);



        return "item/item_list";
    }


    @RequestMapping("/add")
    public String add(@Valid Item item, BindingResult bindingResult,Model model, HttpServletRequest req) throws IOException {

        if(bindingResult.hasErrors()){
            return "item/item_add";
        }
        MultipartFile picFile = item.getPicFile();

        UploadPic uploadPic = new UploadPic(req, picFile).invoke();

        if (uploadPic.is()) {
            return "item/item_add";
        }

        String pic = uploadPic.getPic();

        item.setPic(pic);

        Integer count = itemService.save(item);

        if(count==1){

            return "redirect:/item/list";
        }
        return "item/item_add";
    }


    @RequestMapping(value = "delete/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Integer delete(@PathVariable Integer id){

        Integer count = itemService.deleteById(id);

        return count;
    }




    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String toUpdatePage(@PathVariable Integer id,Model model){

        Item item = itemService.findById(id);

        model.addAttribute("item", item);

        return "item/item_update";
    }





    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Item item, HttpServletRequest req) throws IOException {

        MultipartFile picFile = item.getPicFile();

        if(picFile != null&&picFile.getSize()>0){

            UploadPic uploadPic = new UploadPic(req, picFile).invoke();

            if (uploadPic.is()) {
                return "item/item_update";
            }

            String pic = uploadPic.getPic();
            item.setPic(pic);

        }

        Integer count=itemService.updateById(item);
        if(count==1){

            return "redirect:/item/list";
        }


        return "redirect:/item/update"+item.getId();
    }











    private class UploadPic {
        private boolean myResult;
        private HttpServletRequest req;
        private MultipartFile picFile;
        private String pic;

        public UploadPic(HttpServletRequest req, MultipartFile picFile) {
            this.req = req;
            this.picFile = picFile;
        }

        boolean is() {
            return myResult;
        }

        public String getPic() {
            return pic;
        }

        public UploadPic invoke() throws IOException {
            boolean flag = false;

            String[] Types = picTypes.split("/");


            String filename = picFile.getOriginalFilename();
            for (String type : Types) {
                if(StringUtils.endsWithIgnoreCase(filename,type)){
                    flag = true;
                    break;
                }
            }

            if(flag==false){
                myResult = true;
                return this;
            }

            BufferedImage image = ImageIO.read(picFile.getInputStream());
            if(image==null){
                myResult = true;
                return this;
            }

            String typeName = StringUtils.substringAfterLast(filename, ".").toLowerCase();

            String prefixName = UUID.randomUUID().toString();

            String newName = prefixName + "." +typeName;

            File file = new File(req.getServletContext().getRealPath("/")+"static/images/"+newName);

            picFile.transferTo(file);

            pic = "http://localhost/static/images/" + newName;
            myResult = false;
            return this;
        }
    }
}
