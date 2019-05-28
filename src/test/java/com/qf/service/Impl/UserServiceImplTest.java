package com.qf.service.Impl;

import com.qf.MyTest;
import com.qf.bean.User;
import com.qf.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 刘少楠
 * @Date 2019/05/24
 */
public class UserServiceImplTest extends MyTest {

    @Autowired
    private UserService userService;

    @Test
    public void checkUsername() {

        Integer num = userService.checkUsername("KING");

        System.out.println("count:"+num);
    }


    @Test
    @Transactional
    public void register(){

        User user =new User();

        user.setUsername("张三");
        user.setPassword("2150683");
        user.setPhone("19999996666");

        Integer count = userService.register(user);



        System.out.println("count:"+count);

    }

    @Test
    public void online(){

        User user = userService.online("admin", "admin");
        System.out.println(user);
    }

}