package com.qf.controller;

import com.qf.bean.User;
import com.qf.service.UserService;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘少楠
 * @Date 2019/05/22
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private final String CODE = "USERCODE";

    private final String REGISTER_PAGE = "user/register";

    private final String LOGIN_PAGE="user/login";

    @Value("${yunpian.apikey}")
    private String apikey;

    @Autowired
    private UserService userService;


    @RequestMapping("/register")
    public String registerUI() {

        return REGISTER_PAGE;
    }

    @RequestMapping("/login")
    public String loginUI() {

        return LOGIN_PAGE;
    }




    @RequestMapping("/check")
    @ResponseBody
    public Map<String, Object> check(@RequestBody User user) {

        String username = user.getUsername();

        System.out.println(username);

        Integer count = userService.checkUsername(username);

        Map<String, Object> result = new HashMap<>();

        if (count == 0) {
            result.put("data", true);
        } else {
            result.put("data", false);
        }

        return result;
    }


    @RequestMapping("/sendsms")
    @ResponseBody
    public String sendSms(@RequestParam String phone, HttpSession session) {
        String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        // 将正确的验证码放到session中
        session.setAttribute(CODE, code);
        //初始化clnt,使用单例方式
        YunpianClient clnt = new YunpianClient(apikey).init();
        //发送短信API
        Map<String, String> param = clnt.newParam(2);
        param.put(YunpianClient.MOBILE, phone);
        param.put(YunpianClient.TEXT, "【云片网】您的验证码是" + code);
        Result<SmsSingleSend> r = clnt.sms().single_send(param);
        //获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),其他说明:r.getDetail(),调用异常:r.getThrowable()
        //账户:clnt.user().* 签名:clnt.sign().* 模版:clnt.tpl().* 短信:clnt.sms().* 语音:clnt.voice().* 流量:clnt.flow().* 隐私通话:clnt.call().*
        //释放clnt
        System.out.println(code);
        clnt.close();
        return "ok";
    }


    @RequestMapping("/regist")
    public String regist(@Valid User user, BindingResult bindingResult,
                         String registerCode,HttpSession session) {

        if( StringUtils.isEmpty(registerCode) || bindingResult.hasErrors()){

            bindingResult = null;
            return REGISTER_PAGE;
        }

        String userCode = (String) session.getAttribute(CODE);

        if(registerCode.equals(userCode)){

            Integer count = userService.register(user);

            if(count == 1){

                return "redirect:login";
            }
        }

            return REGISTER_PAGE;



    }


    @RequestMapping("/online")
    public String online(String username, String password,
                         HttpSession session, Model model) {

        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return LOGIN_PAGE;
        }

        User user=userService.online(username,password);
        if(user!=null){
            session.setAttribute("user",user);
            return "redirect:/item/list";
        }else{
            model.addAttribute("loginInfo","用户名或密码错误");
            return LOGIN_PAGE;
        }
    }



}