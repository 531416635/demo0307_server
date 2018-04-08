package com.xiao.demo.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 描述：
 * 作者：yaoyuxiao
 * 时间：2018/4/8 20:44
 */
public interface OrderService {

    /**
     * 获取全部菜单信息
     * @return
     */
    JSONObject createOrder(Map<String,Object> map);
}
