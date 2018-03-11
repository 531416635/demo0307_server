package com.xiao.demo.dao;

import com.xiao.demo.model.UserRoleModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleModelMapper {

    /**
     * 更新用户的角色
     * @param record
     * @return
     */
    int updateByUid(UserRoleModel record);
    /**
     * 删除用户的角色
     * @param id
     * @return
     */
    int deleteByUid(Integer id);

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