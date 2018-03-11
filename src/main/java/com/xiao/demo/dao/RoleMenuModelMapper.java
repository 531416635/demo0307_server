package com.xiao.demo.dao;

import com.xiao.demo.model.RoleMenuModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenuModelMapper {

    /**
     * 通过rId 角色ID查询对应的权限的菜单ID
     * @mbg.generated 2018-03-07
     */
    List<RoleMenuModel> selectByRid(Integer rId);


    /**
     * 通过角色ID删除对应的菜单ID
     * @mbg.generated 2018-03-07
     */
    int deleteByRid(Integer id);
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