package com.xiao.demo.controller.manager;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.config.Constant;
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
    private Constant constant;

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

}
