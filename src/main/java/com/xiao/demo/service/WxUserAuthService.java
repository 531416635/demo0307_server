package com.xiao.demo.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 描述：
 * 作者：yaoyuxiao
 * 时间：2018/3/25 16:58
 */
public interface WxUserAuthService {

    /**
     * 获取用户信息
     */
    JSONObject getUserInfo();


    /**
     * 微信网页授权，获取access_token,并拉取用户信息
     */
    JSONObject getAuth(Map<String,String> map);

    /**
     * 微信jssdk的初始化参数获取
     * @param map
     * @return
     */
    JSONObject getJsSdkConfig(Map<String, String> map);
}
