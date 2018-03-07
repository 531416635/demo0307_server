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
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserModelMapper userDao;

    @Override
    public JSONObject selectAllUser(String currentPage,String pageSize) {
        int totalCount = userDao.selectUserCount();
        if(StringUtils.isEmpty(currentPage) || "null".equals(currentPage)){
            currentPage = "1";
        }
        if(StringUtils.isEmpty(pageSize) || "null".equals(pageSize)){
            pageSize = "10";
        }
        PageModel page = new PageModel(Integer.valueOf(currentPage),totalCount,Integer.valueOf(pageSize));
        logger.info("{}", JSONObject.toJSONString(page));
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        JSONObject json = new JSONObject();
        json.put("page",page);
        json.put("userList",userDao.selectUserByPage(map));
        return json;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(UserModel record) {
        return 0;
    }

    @Override
    public int insertSelective(UserModel record) {
        return 0;
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
