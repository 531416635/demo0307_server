package com.xiao.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.config.ConfUtil;
import com.xiao.demo.controller.wechat.UserAuthController;
import com.xiao.demo.dao.WxUserModelMapper;
import com.xiao.demo.model.WxUserModel;
import com.xiao.demo.service.WeiUserAuthService;
import com.xiao.demo.utils.HTTPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
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


    @Autowired
    WxUserModelMapper wxUserDao;

    @Override
    public JSONObject getAuth(Map<String,String> map) {
        JSONObject json = new JSONObject();

        //code作为换取access_token的票据
        String code = map.get("code");
        //重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
        String state = map.get("state");

        /**
         * 通过code换取网页授权access_token
         */
        String url_param = "https://api.weixin.qq.com/sns/oauth2/access_token";
        Map<String,String> param = new HashMap<>();
        param.put("appid", ConfUtil.getAppID());//第三方用户唯一凭证
        param.put("secret",ConfUtil.getAppSecret());//第三方用户唯一凭证密钥，即appsecret
        param.put("code",code);//
        param.put("grant_type","authorization_code");//

        try {
            //授权获取access_token
            json = JSONObject.parseObject(HTTPUtils.sendGet(url_param,param));
            WxUserModel userModel = new WxUserModel();
            userModel.setAccessToken(json.getString("access_token"));
            userModel.setExpiresIn(json.getString("expires_in"));
            userModel.setRefreshToken(json.getString("refresh_token"));
            userModel.setOpenid(json.getString("openid"));
            userModel.setScope(json.getString("scope"));
            userModel.setCreateTime(new Date());

            /**
             * 拉取用户信息(需scope为 snsapi_userinfo)
             */
            String url_param1 = "https://api.weixin.qq.com/sns/userinfo";
            Map<String,String> param1 = new HashMap<>();
            param1.put("access_token", userModel.getAccessToken());//网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
            param1.put("openid",userModel.getOpenid());//用户的唯一标识
            param1.put("lang","zh_CN");//返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
            json = JSONObject.parseObject(HTTPUtils.sendGet(url_param1,param1));

            userModel.setNickname(json.getString("nickname"));
            userModel.setSex(json.getString("sex"));
            userModel.setProvince(json.getString("province"));
            userModel.setCity(json.getString("city"));
            userModel.setCountry(json.getString("country"));
            userModel.setHeadimgurl(json.getString("headimgurl"));
            userModel.setPrivilege(json.getString("privilege"));
            userModel.setUnionid(json.getString("unionid"));

            wxUserDao.insertSelective(userModel);

        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("========={}",JSONObject.toJSONString(json));
        return json;
    }
}
