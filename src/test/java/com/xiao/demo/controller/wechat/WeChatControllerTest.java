package com.xiao.demo.controller.wechat;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.config.ConfUtil;
import com.xiao.demo.utils.HTTPUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * 描述：
 * 作者：yaoyuxiao
 * 时间：2018/3/25 16:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WeChatControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(WeChatControllerTest.class);

    @Test
    public void getAccessToken() {
        JSONObject json = new JSONObject();
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String,String> map = new HashMap<>();
        map.put("grant_type","client_credential");//获取access_token填写client_credential
        map.put("appid", ConfUtil.getAppID());//第三方用户唯一凭证
        map.put("secret",ConfUtil.getAppSecret());//第三方用户唯一凭证密钥，即appsecret
        try {
            json = JSONObject.parseObject(HTTPUtils.sendGet(url,map));
            //获取接口返回的access_token，用于全局调用微信接口 有效时间2小时
            String access_token = json.get("access_token")+"";
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("返回结果为：{}",JSONObject.toJSONString(json));
    }
}