package com.xiao.demo.dao;

import com.xiao.demo.model.RoleModel;

public interface RoleModelMapper {
    /**
     *
     * @mbg.generated 2018-03-07
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int insert(RoleModel record);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int insertSelective(RoleModel record);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    RoleModel selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int updateByPrimaryKeySelective(RoleModel record);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int updateByPrimaryKey(RoleModel record);
}