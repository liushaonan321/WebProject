package com.qf.service;

import com.qf.bean.User;

/**
 * @author 刘少楠
 * @Date 2019/05/24
 */
public interface UserService {

  Integer checkUsername(String username);

  Integer register(User user);

  User online(String username,String pasword);


}
