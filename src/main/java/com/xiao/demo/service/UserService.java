package com.xiao.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.model.UserModel;

public interface UserService {
    /**
     * 获取全部用户信息
     * @return
     */
    JSONObject selectAllUser(String currentPage, String pageSize);

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