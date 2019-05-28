package com.qf.service;

import com.qf.MyTest;
import com.qf.bean.Item;
import com.qf.util.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author 刘少楠
 * @Date 2019/05/26
 */
public class ItemServiceTest extends MyTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void findItemByCondition() {

        PageInfo<Item> pageInfo = itemService.findItemByCondition(null, 1, 5);

        System.out.println(pageInfo);

    }
}