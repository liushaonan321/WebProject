package com.qf.bean;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author 刘少楠
 * @Date 2019/05/26
 */
@Component
@Data
public class Item {

    private long id;

    @NotBlank(message = "名字不能为空")
    private String name;

    @NotNull(message = "价格不能为空")
    private double price;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date productionDate;

    @NotBlank(message = "描述不能为空")
    private String description;
    private String pic;
    private Date created;
    @NotNull(message = "图片不能为空")
    private MultipartFile picFile;
}
