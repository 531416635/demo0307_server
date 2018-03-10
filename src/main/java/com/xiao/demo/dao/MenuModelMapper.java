package com.xiao.demo.dao;

import com.xiao.demo.model.MenuModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MenuModelMapper {

    /**
     * 查询全部的菜单信息
     * @return
     */
    List<MenuModel> getMenuChildren();

    /**
     * 分页查询全部的菜单信息
     * @return
     */
    List<MenuModel> selectMenuByPage(Map<String, Object> map);

    /**
     * 查询总数
     * @return
     */
    int selectMenuCount();
    /**
     *
     * @mbg.generated 2018-03-10
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-03-10
     */
    int insert(MenuModel record);

    /**
     *
     * @mbg.generated 2018-03-10
     */
    int insertSelective(MenuModel record);

    /**
     *
     * @mbg.generated 2018-03-10
     */
    MenuModel selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-03-10
     */
    int updateByPrimaryKeySelective(MenuModel record);

    /**
     *
     * @mbg.generated 2018-03-10
     */
    int updateByPrimaryKey(MenuModel record);
}