package com.xiao.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.model.MenuModel;

import java.util.Map;

public interface MenuService {
    /**
     * 获取全部菜单信息
     * @return
     */
    JSONObject selectAllMenus(Map<String,Object> map);

    /**
     * 获取全部菜单信息
     * @return
     */
    JSONObject getMenuChildren(Map<String,Object> map);
    /**
     *
     * @mbg.generated 2018-03-07
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int insert(MenuModel record);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int insertSelective(MenuModel record);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    MenuModel selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int updateByPrimaryKeySelective(MenuModel record);

    /**
     *
     * @mbg.generated 2018-03-07
     */
    int updateByPrimaryKey(MenuModel record);
}