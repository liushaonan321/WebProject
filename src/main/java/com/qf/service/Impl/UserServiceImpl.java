package com.qf.service.Impl;

import com.qf.bean.User;
import com.qf.dao.Userdao;
import com.qf.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author 刘少楠
 * @Date 2019/05/24
 */



@Service
public class UserServiceImpl implements UserService {



@Autowired
private Userdao userdao;


    @Override
    public Integer checkUsername(String username) {

        if(!StringUtils.isEmpty(username)){
            username = username.trim();
        }

        Integer num = userdao.check(username);

        return num;
    }




    @Override
    @Transactional
    public Integer register(User user) {

        user.setPassword(new Md5Hash(user.getPassword()).toString());


        return userdao.save(user);
    }

    @Override
    public User online(String username, String password) {

        User user = userdao.findByUsername(username);

        if(user!=null){
            if(user.getPassword().equals(new Md5Hash(password).toString())){

                return  user;
            }
        }
        return null;
    }


}
