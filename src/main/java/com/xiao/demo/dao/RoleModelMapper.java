package com.xiao.demo.dao;

import com.xiao.demo.model.RoleModel;
import com.xiao.demo.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleModelMapper {

    /**
     * 获取全部角色信息
     * @return
     */
    List<UserModel> selectRoleByPage(Map<String,Object> map);

    /**
     * 获取角色数
     * @return
     */
    Integer selectRoleCount();

    /**
     * 获取全部角色信息
     * @return
     */
    List<UserModel> selectAllRole();

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