package com.xiao.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.dao.UserModelMapper;
import com.xiao.demo.model.PageModel;
import com.xiao.demo.model.UserModel;
import com.xiao.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserModelMapper userDao;

    @Override
    public JSONObject selectAllUser(Map<String,Object> map) {
        //分页数据处理
        String currentPage = null;
        String pageSize = null;
        if(null!=map){
            currentPage = map.get("currentPage")+"";
            pageSize = map.get("pageSize")+"";
        }
        int totalCount = userDao.selectUserCount();
        PageModel page = new PageModel(currentPage,totalCount,pageSize);

        Map<String,Object> mapParam = new HashMap<>();
        mapParam.put("page",page);
        JSONObject json = new JSONObject();
        json.put("page",page);
        json.put("userList",userDao.selectUserByPage(mapParam));
        return json;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(UserModel record) {
        return userDao.insert(record);
    }

    @Override
    public int insertSelective(UserModel record) {
        return userDao.insertSelective(record);
    }

    @Override
    public UserModel selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(UserModel record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(UserModel record) {
        return 0;
    }
}
