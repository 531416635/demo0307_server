package com.xiao.demo.dao;

import com.xiao.demo.model.UserRoleModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleModelMapper {
    /**
     *
     * @mbg.generated 2018-03-07
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int insert(UserRoleModel record);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int insertSelective(UserRoleModel record);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    UserRoleModel selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int updateByPrimaryKeySelective(UserRoleModel record);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int updateByPrimaryKey(UserRoleModel record);
}