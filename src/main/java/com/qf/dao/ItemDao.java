package com.qf.dao;

import com.qf.bean.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 刘少楠
 * @Date 2019/05/26
 */
public interface ItemDao {

   Integer findCountByNameLike(@Param("name")String name);

   List<Item> showByName(@Param("name")String name,
                         @Param("offset")Integer offset,
                         @Param("size")Integer size);

   Integer save(Item item);


   Integer deleteById(@Param("id")Integer id);


   Item findById(@Param("id")Integer id);


   Integer updateById(Item item);

}
