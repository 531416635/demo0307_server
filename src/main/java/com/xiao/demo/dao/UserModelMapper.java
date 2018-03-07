package com.xiao.demo.dao;

import com.xiao.demo.model.UserModel;

public interface UserModelMapper {
    /**
     *
     * @mbg.generated 2018-03-07
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int insert(UserModel record);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int insertSelective(UserModel record);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    UserModel selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int updateByPrimaryKeySelective(UserModel record);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int updateByPrimaryKey(UserModel record);
}