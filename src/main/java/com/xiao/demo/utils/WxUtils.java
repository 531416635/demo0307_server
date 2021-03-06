package com.xiao.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.config.ConfUtil;
import com.xiao.demo.model.WxTemplate;
import com.xiao.demo.model.WxTemplateParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import sun.security.pkcs11.wrapper.Constants;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：微信公众号相关工具类
 * 作者：yaoyuxiao
 * 时间：2018/4/5  15:52
 */
public class WxUtils {

    public static Logger logger = LoggerFactory.getLogger(WxUtils.class);

    /**
     * 获取基础支持的accessToken，用于全局调用微信接口；
     * 注意区分网页授权的网页授权access_token
     * @return
     */
    public static JSONObject getAccessToken(){
        JSONObject json = new JSONObject();
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String,String> map = new HashMap<>();
        map.put("grant_type","client_credential");//获取access_token填写client_credential
        map.put("appid", ConfUtil.getAppID());//第三方用户唯一凭证
        map.put("secret",ConfUtil.getAppSecret());//第三方用户唯一凭证密钥，即appsecret
        try {
            json = JSONObject.parseObject(HTTPUtils.sendGet(url,map));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }


    /**
     * 获取前台微信jssdk所需参数
     * @param accessToken
     * @return
     */
    public static JSONObject getJsapiTicket(String accessToken){
        String js_api_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        JSONObject json = new JSONObject();
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

    /**
     * 模板消息 ID --  提交订单之后的发送订单消息
     * @param touser    接收者openid
     * @param template_id   	模板ID
     * @param url   模板跳转链接
     * @param paras  模板数据
     * @param accessToken
     */
    public static void sendTemplateOrder(String touser, String template_id, String url, List<WxTemplateParam> paras, String accessToken){

        String templateUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;

        WxTemplate tem=new WxTemplate();
        tem.setTemplateId(template_id);
        tem.setTopColor("#00DD00");
        tem.setToUser(touser);
        tem.setUrl(url);

        tem.setTemplateParamList(paras);
        try {
           String result =  HTTPUtils.sendPost(templateUrl,tem.toJSON());
            logger.info("模板消息 ID=={}",result);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
