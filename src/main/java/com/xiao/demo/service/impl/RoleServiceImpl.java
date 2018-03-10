package com.xiao.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.dao.RoleModelMapper;
import com.xiao.demo.vo.PageModel;
import com.xiao.demo.model.RoleModel;
import com.xiao.demo.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * 作者：yaoyuxiao
 * 时间：2018/3/8 21:53
 */
@Service
public class RoleServiceImpl implements RoleService{

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    RoleModelMapper roleDao;

    @Override
    public JSONObject selectAllRole(Map<String,Object> map) {
        //分页数据处理
        String currentPage = null;
        String pageSize = null;
        if(null!=map){
            currentPage = map.get("currentPage")+"";
            pageSize = map.get("pageSize")+"";
        }
        int totalCount = roleDao.selectRoleCount();
        PageModel page = new PageModel(currentPage,totalCount,pageSize);

        Map<String,Object> mapParam = new HashMap<>();
        mapParam.put("page",page);
        JSONObject json = new JSONObject();
        json.put("page",page);
        json.put("roleList",roleDao.selectRoleByPage(mapParam));
        return json;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(RoleModel record) {
        return 0;
    }

    @Override
    public int insertSelective(RoleModel record) {
        return 0;
    }

    @Override
    public RoleModel selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(RoleModel record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(RoleModel record) {
        return 0;
    }
}