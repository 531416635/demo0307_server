package com.xiao.demo.controller.manager;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.model.UserModel;
import com.xiao.demo.service.UserService;
import com.xiao.demo.utils.AESUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 描述：用户管理
 * 作者：yaoyuxiao
 * 时间：2018/3/7  13:34
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private  static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     * @return
     */
    @RequestMapping(value = "/getAllUser.do",produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject getAllUser(@RequestBody(required = false) Map<String,Object> map){
        JSONObject json = new JSONObject();
        json.put("code","-1");
        try{
            json.put("code","1");
            json.put("result",userService.selectAllUser(map));
        }catch (Exception e){
            e.printStackTrace();
            json.put("code","-2");
            json.put("msg","获取用户信息异常");
        }
        return json;
    }

    /**
     * 添加用户
     * @return
     */
    @RequestMapping(value = "/addUser.do",produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject addUser(@RequestBody(required = false)UserModel userModel){
        JSONObject json = new JSONObject();
        json.put("code","-1");
        try{
            userModel.setUserPassword(AESUtils.encrypt(userModel.getUserPassword()));
            logger.info(JSONObject.toJSONString(userModel));
            json.put("code","1");
            json.put("result",userService.insertSelective(userModel));
        }catch (Exception e){
            e.printStackTrace();
            json.put("code","-2");
            json.put("msg","添加用户");
        }
        return json;
    }

    /**
     * 修改用户
     * @return
     */
    @RequestMapping(value = "/editUser.do",produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject editUser(@RequestBody(required = false)Map<String,Object> map){
        JSONObject json = new JSONObject();
        json.put("code","-1");
        try{
            UserModel userModel = new UserModel();
            userModel.setId(Integer.valueOf(map.get("id")+""));
            userModel.setUserPassword(AESUtils.encrypt(map.get("user_password")+""));
            userModel.setRoleId(Integer.valueOf(map.get("r_id")+""));
            userModel.setUserName(map.get("user_name")+"");
            json.put("code","1");
            json.put("result",userService.updateByPrimaryKeySelective(userModel));
        }catch (Exception e){
            e.printStackTrace();
            json.put("code","-2");
            json.put("msg","修改用户信息");
        }
        return json;
    }

    /**
     * 删除用户
     * @return
     */
    @RequestMapping(value = "/deleteUser.do",produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject deleteUser(@RequestBody(required = false)Map<String,Object> map){
        JSONObject json = new JSONObject();
        json.put("code","-1");
        try{
            int  key = Integer.valueOf(map.get("id")+"");
            json.put("code","1");
            json.put("result",userService.deleteByPrimaryKey(key));
        }catch (Exception e){
            e.printStackTrace();
            json.put("code","-2");
            json.put("msg","修改用户信息");
        }
        return json;
    }

}
