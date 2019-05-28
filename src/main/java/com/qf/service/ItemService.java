package com.qf.service;

import com.qf.bean.Item;
import com.qf.util.PageInfo;

/**
 * @author 刘少楠
 * @Date 2019/05/26
 */
public interface ItemService {

    PageInfo<Item> findItemByCondition(String name, Integer page, Integer size);

    Integer save(Item item);

    Integer deleteById(Integer id);

    Item findById(Integer id);

    Integer updateById(Item item);





}
