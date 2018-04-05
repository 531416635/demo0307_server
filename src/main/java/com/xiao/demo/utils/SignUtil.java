package com.xiao.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.config.ConfUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 微信公众号
 * 请求数据校验工具类
 * 
 * @author yaoyuxiao
 *
 */
public class SignUtil {

	// 与接口配置信息中的taken要一致
	private static String token = "success0309";

	/**
	 * 验证签名
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */

	public static boolean checkSignature(String signature, String timestamp,
			String nonce) {
		String[] arr = new String[] { token, timestamp, nonce };
		// 将token、timestamp、 nonce三个参数进行字典排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;

		try {
			md = MessageDigest.getInstance("SHA-1");
			// 将三个参数字符串拼接成一个字符串进行SHA-1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		content = null;
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}

	/**
	 * 将字符节数组转换成十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";

		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字符节转换成十六进制字符串
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = digit[(mByte >>> 4) & 0x0F];
		tempArr[1] = digit[mByte & 0x0F];
		String s = new String(tempArr);
		return s;
	}


	/**
	 * 获取微信jssdk的签名
	 * @param jsapi_ticket
	 * @param url
	 * @return
	 */
	public static JSONObject sign(String jsapi_ticket, String url) {
		JSONObject ret = new JSONObject();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";
		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str
				+ "&timestamp=" + timestamp + "&url=" + url;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ret.put("url", url);
		//注意这里 要加上自己的appId
		ret.put("appId", ConfUtil.getAppID());
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash)
		{
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}
	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
}
