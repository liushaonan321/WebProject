package com.qf.dao;

import com.qf.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 刘少楠
 * @Date 2019/05/24
 */
@Repository
public interface Userdao {

//    检查用户名是否存
    Integer check (@Param("username") String username);


//    保存注册信息
    Integer save(User user);


    //核对并获取账号信息
    User findByUsername(@Param("username") String username);



}
