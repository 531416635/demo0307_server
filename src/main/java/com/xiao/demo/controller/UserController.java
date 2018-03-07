package com.xiao.demo.controller;

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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 描述：
 * 作者：yaoyuxiao
 * 时间：2018/3/7  13:34
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    final  static Logger logger = LoggerFactory.getLogger(UserController.class);

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
        String currentPage = null;
        String pageSize = null;
        if(null!=map){
            currentPage = map.get("currentPage")+"";
            pageSize = map.get("pageSize")+"";
        }
        JSONObject json = new JSONObject();
        json.put("code","-1");
        try{
            json.put("code","1");
            json.put("result",userService.selectAllUser(currentPage,pageSize));
        }catch (Exception e){
            e.printStackTrace();
            json.put("code","-2");
            json.put("msg","获取用户信息异常");
        }
        return json;
    }


    /**
     * 登录校验
     * @param userInfoModel
     * @return
     */
    @RequestMapping(value = "/toLogin.do",produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject toLogin(@RequestBody UserModel userInfoModel){
        HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes( ) ).getRequest( );
        HttpSession session = request.getSession();
        JSONObject json = new JSONObject();
        json.put("code","-1");
        return json;
    }

    /**
     * 解密医院用户的身份证号码
     * @return
     */
    @RequestMapping(value = "/getIdCard.do",produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject getIdCard(@RequestBody Map<String,Object> map){
        JSONObject json = new JSONObject();
//        json.put("code","-1");
        try {
            System.out.println(map.get("input_key"));
            json.put("name", AESUtils.decrypt(map.get("input_key")+""));
            json.put("type", "success");
        }catch (Exception e){
//            json.put("code","-1");

        }
        return json;
    }
}
