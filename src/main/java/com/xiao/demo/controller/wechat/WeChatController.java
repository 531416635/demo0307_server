package com.xiao.demo.controller.wechat;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.config.ConfUtil;
import com.xiao.demo.utils.HTTPUtils;
import com.xiao.demo.utils.JedisManager;
import com.xiao.demo.utils.MessageUtil;
import com.xiao.demo.utils.SignUtil;
import com.xiao.demo.vo.TextMeaasge;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * 作者：yaoyuxiao
 * 时间：2018/3/25 16:54
 */
@Controller
@RequestMapping(value = "/wechat")
public class WeChatController {

	private static final Logger logger = LoggerFactory.getLogger(WeChatController.class);


	@Autowired
	JedisManager jedisManager;

	/**
	 * 主要用于验证服务器
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/banding.do", method = RequestMethod.GET)
	public void getMenuList(HttpServletRequest request,
			HttpServletResponse response) {
		// signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
		String signature = request.getParameter("signature");
		// timestamp 时间戳
		String timestamp = request.getParameter("timestamp");
		// nonce 随机数
		String nonce = request.getParameter("nonce");
		// echostr 随机字符串
		String echostr = request.getParameter("echostr");

		logger.info("signature{},timestamp{},nonce{},echostr{}", signature,
				timestamp, nonce, echostr);
		try {
			PrintWriter out = response.getWriter();
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				out.print(echostr);
			}
			out.close();
			out = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/***
	 * 主要为了发送消息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/banding.do", method = RequestMethod.POST)
	@ResponseBody
	public void getWeiXinMessage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			Map<String, String> map = MessageUtil.xmlToMap(request);
			String toUserName = map.get("ToUserName");
			String fromUserName = map.get("FromUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");

			HttpSession session = request.getSession();
			session.setAttribute("openId",fromUserName);
			logger.info("ToUserName{},FromUserName{},msgType{},content{}", toUserName,fromUserName,msgType,content);
			String message = null;
//			if ("text".equals(msgType)) { // 对文本消息进行处理
//				TextMeaasge text = new TextMeaasge();
//				text.setFromUserName(toUserName); // 发送和回复是反向的
//				text.setToUserName(fromUserName);
//				text.setMsgType("text");
//				text.setCreateTime(new Date().getTime());
//				text.setContent("你发送的消息是：" + content);
//				message = MessageUtil.textMessageToXML(text);
//				System.out.println(message);
//				out.print(message); // 将回应发送给微信服务器
//			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * 获取access_token
	 */
	@RequestMapping(value = "/getAccessToken.do", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getAccessToken(){
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

//			jedisManager.
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("",json);
		return json;
	}

}
