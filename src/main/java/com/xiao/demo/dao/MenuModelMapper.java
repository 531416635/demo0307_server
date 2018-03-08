package com.xiao.demo.dao;

import com.xiao.demo.model.MenuModel;
import com.xiao.demo.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MenuModelMapper {

    /**
     * 获取全部菜单信息
     * @return
     */
    List<UserModel> selectMenuByPage(Map<String,Object> map);

    /**
     * 获取菜单数
     * @return
     */
    Integer selectMenuCount();
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