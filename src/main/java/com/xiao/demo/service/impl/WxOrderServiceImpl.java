package com.xiao.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.config.ConfUtil;
import com.xiao.demo.dao.WxOrderModelMapper;
import com.xiao.demo.model.WxOrderModel;
import com.xiao.demo.model.WxTemplate;
import com.xiao.demo.model.WxTemplateParam;
import com.xiao.demo.service.WxOrderService;
import com.xiao.demo.utils.JedisManager;
import com.xiao.demo.utils.SequenceUtils;
import com.xiao.demo.utils.WxUtils;
import com.xiao.demo.vo.PageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

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

    @Autowired
    JedisManager jedisManager;
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

        //推送模板消息
        List<WxTemplateParam> paras=new ArrayList<WxTemplateParam>();
        paras.add(new WxTemplateParam("orderId",orderId,"#173177"));
        paras.add(new WxTemplateParam("title",title,"#173177"));
        paras.add(new WxTemplateParam("question",question,"#173177"));
        paras.add(new WxTemplateParam("addressValue",addressValue,"#173177"));
        paras.add(new WxTemplateParam("address",address,"#173177"));
        paras.add(new WxTemplateParam("phone",phone,"#173177"));

        //获取次数有限 注意缓存全局accessToken
        String accessToken = jedisManager.getValueByKey(openId+"accessToken");
        if(StringUtils.isEmpty(accessToken) || "null".equals(accessToken)) {
            JSONObject result_param = WxUtils.getAccessToken();
            accessToken = result_param.getString("access_token");
            jedisManager.saveValueByKey(openId + "accessToken", accessToken, result_param.getInteger("expires_in"));
        }
        WxUtils.sendTemplateOrder(openId, ConfUtil.getTemplateOrderInit(),"http://www.baidu.com",paras,accessToken);
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

        if(StringUtils.isEmpty(openId) || "null".equals(openId)){
//            openId = "oI2OOwotXwQD-NACEebh7BwXeHzk";
        }
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
        json.put("totalCount",totalCount);

        return json;
    }
}
