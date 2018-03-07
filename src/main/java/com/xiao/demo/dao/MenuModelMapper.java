package com.xiao.demo.dao;

import com.xiao.demo.model.MenuModel;

public interface MenuModelMapper {
    /**
     *
     * @mbg.generated 2018-03-07
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int insert(MenuModel record);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int insertSelective(MenuModel record);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    MenuModel selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int updateByPrimaryKeySelective(MenuModel record);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int updateByPrimaryKey(MenuModel record);
}