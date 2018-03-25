package com.xiao.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.config.ConfUtil;
import com.xiao.demo.controller.wechat.UserAuthController;
import com.xiao.demo.service.WeiUserAuthService;
import com.xiao.demo.utils.HTTPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * 作者：yaoyuxiao
 * 时间：2018/3/25 16:59
 */
@Service
public class WeiUserAuthServiceImpl implements WeiUserAuthService {

    private static final Logger logger = LoggerFactory.getLogger(WeiUserAuthServiceImpl.class);


    @Override
    public JSONObject getAuth(Map<String,String> map) {
        JSONObject json = new JSONObject();
        //code作为换取access_token的票据
        String code = map.get("code");
        //
        String state = map.get("state");
        logger.info("",json);
        return json;
    }
}
