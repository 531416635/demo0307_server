package com.xiao.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.config.ConfUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.pkcs11.wrapper.Constants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：微信公众号相关工具类
 * 作者：yaoyuxiao
 * 时间：2018/4/5  15:52
 */
public class WxUtils {

    public static Logger logger = LoggerFactory.getLogger(WxUtils.class);

    public final static String js_api_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";


    /**
     * 获取accessToken
     * @return
     */
    public static String getAccessToken(){
        JSONObject json = new JSONObject();
        String access_token = null;
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String,String> map = new HashMap<>();
        map.put("grant_type","client_credential");//获取access_token填写client_credential
        map.put("appid", ConfUtil.getAppID());//第三方用户唯一凭证
        map.put("secret",ConfUtil.getAppSecret());//第三方用户唯一凭证密钥，即appsecret
        try {
            json = JSONObject.parseObject(HTTPUtils.sendGet(url,map));
            //获取接口返回的access_token，用于全局调用微信接口 有效时间2小时
            access_token = json.get("access_token")+"";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return access_token;
    }


    /**
     * 获取前台微信jssdk所需参数
     * @param accessToken
     * @return
     */
    public static JSONObject getJsapiTicket(String accessToken){
        JSONObject json = new JSONObject();
        if(accessToken==null){
            accessToken =getAccessToken();
        }
        String requestUrl = js_api_ticket_url.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = null;
        try {
            String result = HTTPUtils.sendGet(requestUrl,new HashMap<String, String>());
            jsonObject = JSONObject.parseObject(result);
            logger.info("获取前台微信jssdk所需参数==={}",result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(jsonObject.getString("errcode").equals("0")){
            json.put("ticket",jsonObject.getString("ticket"));
            json.put("expiresIn",jsonObject.getString("expires_in"));
        }else{
            logger.error("error");
        }
        return json;
    }

}
