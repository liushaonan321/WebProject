package com.qf.controller;

import com.qf.MyTest;
import com.qf.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author 刘少楠
 * @Date 2019/05/24
 */
public class UserControllerTest extends MyTest {

    @Autowired
    private UserService userService;

//    @Test
//    public void check() {
//
//        Integer num = userService.checkUsername("KING");
//
//        System.out.println("======count:"+num);
//
//    }
}