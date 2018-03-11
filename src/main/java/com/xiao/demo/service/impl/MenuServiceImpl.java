package com.xiao.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.dao.MenuModelMapper;
import com.xiao.demo.model.MenuModel;
import com.xiao.demo.service.MenuService;
import com.xiao.demo.vo.PageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public JSONObject getMenuChildren(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        List<MenuModel> result =menuDao.getMenuChildren();
        List<MenuModel> sortMenu = new ArrayList<>();
        if(!CollectionUtils.isEmpty(result)){
            for(MenuModel param1:result){
                //MenuParent 为0，表示最顶级的菜单
                if(0 == param1.getMenuParent()){
                    //处理级联默认显示选项
                    List<Integer> def = new ArrayList<>();
                    def.add(param1.getId());
                    param1.setDefaultSelect(def);
                    //处理子节点
                    List<MenuModel> children = sortMenuRecursion(param1,result);
                    if(!CollectionUtils.isEmpty(children)){
                        param1.setChildren(children);
                    }else{
                        param1.setChildren(null);
                    }
                    sortMenu.add(param1);
                }
            }
        }
        json.put("menuList",sortMenu);
        return json;
    }

    /**
     * 递归算法，获取菜单对应的子菜单
     * @param param1
     * @param result
     * @return
     */
    public List<MenuModel> sortMenuRecursion(MenuModel param1,List<MenuModel> result ){
        List<MenuModel> jsonSort = new ArrayList<>();
        for(MenuModel param2:result){
            if(param1.getId() == param2.getMenuParent()){
                //处理级联默认显示选项
                List<Integer> def = new ArrayList<>();
                def.addAll(param1.getDefaultSelect());
                def.add(param2.getId());
                param2.setDefaultSelect(def);

                //处理子节点
                List<MenuModel> children = sortMenuRecursion(param2,result);
                if(!CollectionUtils.isEmpty(children)){
                    param2.setChildren(children);
                }else{
                    param2.setChildren(null);
                }
                jsonSort.add(param2);
            }
        }
        return jsonSort;
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
