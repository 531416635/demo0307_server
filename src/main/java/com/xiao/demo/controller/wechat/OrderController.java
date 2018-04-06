package com.xiao.demo.controller.wechat;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 描述：
 * 作者：yaoyuxiao
 * 时间：2018/4/6 21:09
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    /**
     * 生成订单
     * @return
     */
    @RequestMapping(value = "/createRegister.do",produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject createRegister(@RequestBody(required = false)Map<String,Object> map){
        JSONObject json = new JSONObject();
        logger.info("生成订单入参===={}",JSONObject.toJSONString(map));
        json.put("code","-1");
        try{
            json.put("code","1");
        }catch (Exception e){
            e.printStackTrace();
            json.put("code","-2");
            json.put("msg","");
        }
        return json;
    }
}
