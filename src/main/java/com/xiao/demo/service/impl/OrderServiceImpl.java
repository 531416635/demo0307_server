package com.xiao.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.dao.WxOrderModelMapper;
import com.xiao.demo.model.WxOrderModel;
import com.xiao.demo.service.OrderService;
import com.xiao.demo.utils.SequenceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * 描述：
 * 作者：yaoyuxiao
 * 时间：2018/4/8 20:45
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    WxOrderModelMapper orderDao;

    @Override
    public JSONObject createOrder(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes( ) ).getRequest( );
        HttpSession session = request.getSession();
        String openId = session.getAttribute("openId")+"";
        JSONObject register = JSONObject.parseObject(map.get("register")+"",JSONObject.class);
        JSONObject addressGps = JSONObject.parseObject(map.get("addressGps")+"",JSONObject.class);

        String type = register.get("type")+"";
        String orderId = SequenceUtils.getOrderNo(openId,type);

        String title = register.get("title")+"";
        String question = register.get("question")+"";
        String address = register.get("address")+"";
        String phone = register.get("phone")+"";

        String detail = addressGps.get("detail")+"";
        String addressValue = map.get("addressValue")+"";

        WxOrderModel orderModel = new WxOrderModel();
        orderModel.setOpenid(openId);
        orderModel.setOrderId(orderId);
        orderModel.setType(type);
        orderModel.setTitle(title);
        orderModel.setQuestion(question);
        orderModel.setCreatetime(new Date());
        orderModel.setPhone(phone);
        orderModel.setGpsAddress(detail);
        orderModel.setArea(addressValue);
        orderModel.setAddress(address);


        json.put("result",orderDao.insertSelective(orderModel));
        return json;
    }
}
