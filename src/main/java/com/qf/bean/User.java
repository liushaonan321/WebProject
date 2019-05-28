package com.qf.bean;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 刘少楠
 * @Date 2019/05/21
 */
@Data
@Component
public class User {

    private long id;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "手机号不能为空")
    private String phone;

    private Date created;




}
