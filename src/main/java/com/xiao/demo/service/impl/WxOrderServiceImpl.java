package com.xiao.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.dao.WxOrderModelMapper;
import com.xiao.demo.model.WxOrderModel;
import com.xiao.demo.service.WxOrderService;
import com.xiao.demo.utils.SequenceUtils;
import com.xiao.demo.vo.PageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * 作者：yaoyuxiao
 * 时间：2018/4/8 20:45
 */
@Service
public class WxOrderServiceImpl implements WxOrderService {

    private static Logger logger = LoggerFactory.getLogger(WxOrderServiceImpl.class);

    @Autowired
    WxOrderModelMapper orderDao;

    /**
     * 生成订单
     * @return
     */
    @Override
    public JSONObject createOrder(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes( ) ).getRequest( );
        HttpSession session = request.getSession();
        String openId = session.getAttribute("openId")+"";
        Map register = (Map) map.get("register");
        Map addressGps = (Map) map.get("addressGps");

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
        orderModel.setOrderStatus("0");//待付款

        json.put("result",orderDao.insertSelective(orderModel));
        return json;
    }

    /**
     * 获取订单信息(支持分页获取)
     *
     * @param map
     * @return
     */
    @Override
    public JSONObject getOrder(Map<String, Object> map) {

        JSONObject json = new JSONObject();

        HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes( ) ).getRequest( );
        HttpSession session = request.getSession();
        String openId = session.getAttribute("openId")+"";

        //分页数据处理
        String currentPage = null;
        String pageSize = null;

        String orderStatus = null;
        if(null!=map){
            currentPage = map.get("currentPage")+"";
            pageSize = map.get("pageSize")+"";
            orderStatus = map.get("orderStatus")+"";
        }
        Map<String, String> mapPage = new HashMap<>();
        mapPage.put("orderStatus",orderStatus);
        mapPage.put("openid",openId);
        int totalCount = orderDao.selectCountByOrderStatus(mapPage);

        PageModel page = new PageModel(currentPage,totalCount,pageSize);

        Map<String,Object> mapParam = new HashMap<>();
        mapParam.put("page",page);
        mapParam.put("orderStatus",orderStatus);
        mapParam.put("openid",openId);

        json.put("result",orderDao.selectOrderByPage(mapParam));

        return json;
    }
}
