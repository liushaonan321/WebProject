package com.qf.service.Impl;

import com.qf.bean.Item;
import com.qf.dao.ItemDao;
import com.qf.service.ItemService;
import com.qf.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘少楠
 * @Date 2019/05/26
 */



@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;


    @Override
    public PageInfo<Item> findItemByCondition(String name, Integer page, Integer size) {

        Integer count = itemDao.findCountByNameLike(name);

        PageInfo pageInfo = new PageInfo(page, size, count);

        System.out.println(pageInfo);

        List<Item> list = itemDao.showByName(name, pageInfo.getOffset(), pageInfo.getSize());

        pageInfo.setList(list);

        return pageInfo;
    }

    @Override
    public Integer save(Item item) {


        return itemDao.save(item);
    }

    @Override
    public Integer deleteById(Integer id) {

        return itemDao.deleteById(id);
    }


    @Override
    public Item findById(Integer id) {

        return itemDao.findById(id);
    }

    @Override
    public Integer updateById(Item item) {

        return itemDao.updateById(item);
    }
}
