package com.xiao.demo.dao;

import com.xiao.demo.model.RoleMenuModel;

public interface RoleMenuModelMapper {
    /**
     *
     * @mbg.generated 2018-03-07
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int insert(RoleMenuModel record);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int insertSelective(RoleMenuModel record);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    RoleMenuModel selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int updateByPrimaryKeySelective(RoleMenuModel record);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int updateByPrimaryKey(RoleMenuModel record);
}