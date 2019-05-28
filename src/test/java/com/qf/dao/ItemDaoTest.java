package com.qf.dao;

import com.qf.MyTest;
import com.qf.bean.Item;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author 刘少楠
 * @Date 2019/05/26
 */
public class ItemDaoTest extends MyTest {

    @Autowired
    private ItemDao itemDao;

    @Test
    public void findByNameLike() {

        Integer count = itemDao.findCountByNameLike("车");
        System.out.println("count:"+count);
    }


    @Test
    public void showByName(){

        List<Item> items = itemDao.showByName(null, 1, 5);

        for (Item item : items) {

            System.out.println(item);
        }

    }



    @Test
    public void save(){
       Item item=new Item();
       item.setName("233222");
       item.setPrice(8889999);
       item.setDescription("good");
       item.setProductionDate(new Date());
       item.setPic("iamgesssssss");
        Integer count = itemDao.save(item);
        System.out.println("count:"+count);

    }


    @Test
    @Transactional
    public void deleteById(){
        Integer count = itemDao.deleteById(12);
        System.out.println("count:"+count);
    }



    @Test
    public void findByID(){
        Item item = itemDao.findById(10);
        System.out.println(item);
    }



    @Test
    public void updateById(){
        Item item=new Item();
        item.setId(10L);
        item.setName("233222");
        item.setPrice(8889999);
        item.setDescription("good");
        item.setProductionDate(new Date());
        item.setPic("iamgesssssss");

        Integer count = itemDao.updateById(item);
        System.out.println("count:"+count);
    }

}