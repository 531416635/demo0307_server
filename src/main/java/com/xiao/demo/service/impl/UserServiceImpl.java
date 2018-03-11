package com.xiao.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.dao.UserModelMapper;
import com.xiao.demo.dao.UserRoleModelMapper;
import com.xiao.demo.model.UserRoleModel;
import com.xiao.demo.utils.AESUtils;
import com.xiao.demo.vo.PageModel;
import com.xiao.demo.model.UserModel;
import com.xiao.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserModelMapper userDao;

    @Autowired
    UserRoleModelMapper userRoleDao;

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
        List<Map<String,Object>> userList = userDao.selectUserByPage(mapParam);
        List<Map<String,Object>> new_userList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(userList)){
            for(Map<String,Object> mapValue : userList){
                mapValue.put("user_password", AESUtils.decrypt(mapValue.get("user_password")+""));
                new_userList.add(mapValue);
            }
        }
        json.put("userList",new_userList);
        return json;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        int u = userDao.deleteByPrimaryKey(id);
        userRoleDao.deleteByUid(id);
        return u;
    }

    @Override
    public int insert(UserModel record) {
        return userDao.insert(record);
    }

    @Override
    public int insertSelective(UserModel record) {

        int u = userDao.insertSelective(record);
        UserRoleModel userRoleModel = new UserRoleModel();
        userRoleModel.setuId(record.getId());
        userRoleModel.setrId(record.getRoleId());
        userRoleDao.insertSelective(userRoleModel);

        return u ;
    }

    @Override
    public UserModel selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(UserModel record) {
        userDao.updateByPrimaryKeySelective(record);

        UserRoleModel userRoleModel = new UserRoleModel();
        userRoleModel.setuId(record.getId());
        userRoleModel.setrId(record.getRoleId());

        return userRoleDao.updateByUid(userRoleModel);
    }

    @Override
    public int updateByPrimaryKey(UserModel record) {
        return 0;
    }
}
