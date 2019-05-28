package com.qf.dao;

import com.qf.MyTest;
import com.qf.bean.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 刘少楠
 * @Date 2019/05/24
 */
public class UserdaoTest extends MyTest {

    @Autowired
    private Userdao userdao;


    @Test
    public void findAll() {

        Integer num = userdao.check("KING");

        System.out.println("count:"+num);
    }


    @Test
    @Transactional
    public void save(){

        User user =new User();

        user.setUsername("李四");
        user.setPassword("2152671");
        user.setPhone("18888886666");
        Integer count = userdao.save(user);
        System.out.println("count:"+count);

    }

    @Test
    public void findByUsername(){

        User user = userdao.findByUsername("admin");

        System.out.println(user);
    }

}