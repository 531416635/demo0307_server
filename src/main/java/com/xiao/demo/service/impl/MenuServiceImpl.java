package com.xiao.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.dao.MenuModelMapper;
import com.xiao.demo.model.MenuModel;
import com.xiao.demo.model.PageModel;
import com.xiao.demo.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * 作者：yaoyuxiao
 * 时间：2018/3/8 21:58
 */
@Service
public class MenuServiceImpl implements MenuService{

    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    MenuModelMapper menuDao;

    @Override
    public JSONObject selectAllMenus(Map<String, Object> map) {
        //分页数据处理
        String currentPage = null;
        String pageSize = null;
        if(null!=map){
            currentPage = map.get("currentPage")+"";
            pageSize = map.get("pageSize")+"";
        }
        int totalCount = menuDao.selectMenuCount();
        PageModel page = new PageModel(currentPage,totalCount,pageSize);

        Map<String,Object> mapParam = new HashMap<>();
        mapParam.put("page",page);
        JSONObject json = new JSONObject();
        json.put("page",page);
        json.put("menuList",menuDao.selectMenuByPage(mapParam));
        return json;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(MenuModel record) {
        return 0;
    }

    @Override
    public int insertSelective(MenuModel record) {
        return 0;
    }

    @Override
    public MenuModel selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(MenuModel record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(MenuModel record) {
        return 0;
    }
}
