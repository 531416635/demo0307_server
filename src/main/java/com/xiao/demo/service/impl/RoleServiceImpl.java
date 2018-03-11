package com.xiao.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.dao.RoleMenuModelMapper;
import com.xiao.demo.dao.RoleModelMapper;
import com.xiao.demo.model.RoleMenuModel;
import com.xiao.demo.vo.PageModel;
import com.xiao.demo.model.RoleModel;
import com.xiao.demo.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    RoleMenuModelMapper roleMenuDao;

    @Override
    public JSONObject selectAllRoleByPage(Map<String,Object> map) {
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
    public JSONObject selectAllRole() {
        JSONObject json = new JSONObject();
        json.put("roleList",roleDao.selectAllRole());
        return json;
    }

    @Override
    public JSONObject getRoleAuth(RoleModel record) {
        JSONObject json = new JSONObject();
        List<Integer> menus = new ArrayList<>();
        List<RoleMenuModel> menuModels = roleMenuDao.selectByRid(record.getId());
        if(!CollectionUtils.isEmpty(menuModels)){
            for (RoleMenuModel menuModel : menuModels){
                menus.add(menuModel.getmId());
            }
        }
        json.put("menuAuth",menus);
        return json;
    }

    @Override
    public JSONObject addRoleAuth(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        String menus = map.get("menus")+"";
        String[] arr = menus.replace("[","")
                .replace("]","")
                .split(",");
        String roleId = map.get("roleId")+"";

        if(!StringUtils.isEmpty(roleId) && arr!=null && arr.length >0){
            json.put("result",roleMenuDao.deleteByRid(Integer.valueOf(roleId)));
            for(String param:arr){
                RoleMenuModel model = new RoleMenuModel();
                model.setmId(Integer.valueOf(param));
                model.setrId(Integer.valueOf(roleId));

                roleMenuDao.insertSelective(model);
            }
            return json;
        }

        return null;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return roleDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RoleModel record) {
        return 0;
    }

    @Override
    public int insertSelective(RoleModel record) {
        return roleDao.insertSelective(record);
    }

    @Override
    public RoleModel selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(RoleModel record) {
        return roleDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RoleModel record) {
        return 0;
    }
}
