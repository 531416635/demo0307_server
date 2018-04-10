package com.xiao.demo.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 描述：
 * 作者：yaoyuxiao
 * 时间：2018/4/8 20:44
 */
public interface WxOrderService {

    /**
     * 生成订单
     * @return
     */
    JSONObject createOrder(Map<String,Object> map);

    /**
     * 获取订单信息(支持分页获取)
     * @return
     */
    JSONObject getOrder(Map<String,Object> map);
}
