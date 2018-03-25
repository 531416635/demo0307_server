package com.xiao.demo.controller.manager;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.model.RoleModel;
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
            json.put("msg","获取分页角色信息异常");
        }
        return json;
    }

    /**
     * 获取全部角色列表（用户的角色选择）
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
            json.put("msg","获取全部角色信息异常");
        }
        return json;
    }

    /**
     * 新增角色
     * @return
     */
    @RequestMapping(value = "/addRole.do",produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject addRole(@RequestBody(required = false)RoleModel model){
        JSONObject json = new JSONObject();
        json.put("code","-1");
        try{
            json.put("code","1");
            json.put("result",roleService.insertSelective(model));
        }catch (Exception e){
            e.printStackTrace();
            json.put("code","-2");
            json.put("msg","新增角色异常");
        }
        return json;
    }

    /**
     * 修改角色
     * @return
     */
    @RequestMapping(value = "/editRole.do",produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject editRole(@RequestBody(required = false)RoleModel model){
        JSONObject json = new JSONObject();
        json.put("code","-1");
        try{
            json.put("code","1");
            json.put("result",roleService.updateByPrimaryKeySelective(model));
        }catch (Exception e){
            e.printStackTrace();
            json.put("code","-2");
            json.put("msg","修改角色信息异常");
        }
        return json;
    }

    /**
     * 删除角色
     * @return
     */
    @RequestMapping(value = "/deleteRole.do",produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject deleteRole(@RequestBody(required = false)RoleModel model){
        JSONObject json = new JSONObject();
        json.put("code","-1");
        try{
            json.put("code","1");
            json.put("result",roleService.deleteByPrimaryKey(model.getId()));
        }catch (Exception e){
            e.printStackTrace();
            json.put("code","-2");
            json.put("msg","删除角色异常");
        }
        return json;
    }

    /**
     * 查询权限（获取角色对应权限的菜单ID）
     * @return
     */
    @RequestMapping(value = "/getRoleAuth.do",produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject getRoleAuth(@RequestBody(required = false)RoleModel model){
        JSONObject json = new JSONObject();
        json.put("code","-1");
        try{
            json.put("code","1");
            json.put("result",roleService.getRoleAuth(model));
        }catch (Exception e){
            e.printStackTrace();
            json.put("code","-2");
            json.put("msg","查询权限异常");
        }
        return json;
    }

    /**
     * 执行赋权操作（将角色对应权限的菜单ID保存起来）
     * @return
     */
    @RequestMapping(value = "/addRoleAuth.do",produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject addRoleAuth(@RequestBody(required = false)Map<String,Object> map){
        JSONObject json = new JSONObject();
        json.put("code","-1");
        try{
            json.put("code","1");
            logger.info(JSONObject.toJSONString(map));
            json.put("result",roleService.addRoleAuth(map));
        }catch (Exception e){
            e.printStackTrace();
            json.put("code","-2");
            json.put("msg","执行赋权操作异常");
        }
        return json;
    }
}
