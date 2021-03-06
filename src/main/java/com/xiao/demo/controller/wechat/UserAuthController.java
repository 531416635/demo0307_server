package com.xiao.demo.controller.wechat;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.config.ConfUtil;
import com.xiao.demo.service.WxUserAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 描述：
 * 作者：yaoyuxiao
 * 时间：2018/3/25 16:54
 */
@Controller
@RequestMapping(value = "/wechat")
public class UserAuthController {

    private static final Logger logger = LoggerFactory.getLogger(UserAuthController.class);

    @Autowired
    WxUserAuthService userAuthService;

    /**
     * 获取用户信息
     */
    @RequestMapping(value = "/getUserInfo.do",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject getUserInfo(){
        JSONObject json = new JSONObject();
        json.put("code","-1");
        try {
            json = userAuthService.getUserInfo();
            json.put("code","1");
            logger.info("获取用户信息出参-------{}",JSONObject.toJSONString(json));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    /**
     * 微信网页授权回调地址，获取access_token并拉取用户信息
     */
    @RequestMapping(value = "/getAuth.do")
    public String getAuth(@RequestParam(required = false) Map<String,String> map){
        JSONObject json = new JSONObject();
        json.put("code","-1");
        try {
            json = userAuthService.getAuth(map);
            json.put("code","1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("获取用户信息出参-------{}",JSONObject.toJSONString(json));
        return "redirect:"+ ConfUtil.getDemouiIndex();
    }


    /**
     * 微信jssdk的初始化参数获取
     */
    @RequestMapping(value = "/getJsSdkConfig.do",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject getJsSdkConfig(@RequestBody(required = false) Map<String,String> map){
        JSONObject json = new JSONObject();
        json.put("code","-1");
        try {
            json = userAuthService.getJsSdkConfig(map);
            json.put("code","1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

}
