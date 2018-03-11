package com.xiao.demo.dao;

import com.xiao.demo.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserModelMapper {

    /**
     * 获取全部用户信息
     * @return
     */
    List<Map<String,Object>> selectUserByPage(Map<String,Object> map);

    /**
     * 获取用户数
     * @return
     */
    Integer selectUserCount();
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