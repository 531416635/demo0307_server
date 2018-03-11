package com.xiao.demo.controller.manager;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.config.Constant;
import com.xiao.demo.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 描述：角色管理
 * 作者：yaoyuxiao
 * 时间：2018/3/8 21:52
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {
    private  static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private Constant constant;

    @Autowired
    private RoleService roleService;

    /**
     * 获取角色列表(分页)
     * @return
     */
    @RequestMapping(value = "/selectAllRoleByPage.do",produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject selectAllRoleByPage(@RequestBody(required = false) Map<String,Object> map){
        JSONObject json = new JSONObject();
        json.put("code","-1");
        try{
            json.put("code","1");
            json.put("result",roleService.selectAllRoleByPage(map));
        }catch (Exception e){
            e.printStackTrace();
            json.put("code","-2");
            json.put("msg","获取角色信息异常");
        }
        return json;
    }

    /**
     * 获取全部角色列表
     * @return
     */
    @RequestMapping(value = "/getAllRole.do",produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject getAllRole(){
        JSONObject json = new JSONObject();
        json.put("code","-1");
        try{
            json.put("code","1");
            json.put("result",roleService.selectAllRole());
        }catch (Exception e){
            e.printStackTrace();
            json.put("code","-2");
            json.put("msg","获取角色信息异常");
        }
        return json;
    }

}
