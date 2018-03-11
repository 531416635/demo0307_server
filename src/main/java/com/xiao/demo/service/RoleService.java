package com.xiao.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.model.RoleModel;

import java.util.Map;

public interface RoleService {

    /**
     * 获取全部角色信息
     * @return
     */
    JSONObject selectAllRoleByPage(Map<String,Object> map);

    /**
     * 获取全部角色信息
     * @return
     */
    JSONObject selectAllRole();

    /**
     * 查询权限（获取角色对应权限的菜单ID）
     * @return
     */
    JSONObject getRoleAuth(RoleModel record);

    /**
     * 保存权限（保存角色对应权限的菜单ID）
     * @return
     */
    JSONObject addRoleAuth(Map<String,Object> map);
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