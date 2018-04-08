package com.xiao.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述：
 * 作者：yaoyuxiao
 * 时间：2018/4/8 20:45
 */
public class SequenceUtils {


	private final static SimpleDateFormat yy_mm_ddFormat = new SimpleDateFormat("yyMMdd");

	/**
	 * 订单号生成规则
	 * @param openid
	 * @param transType
	 * @return
	 */
	public static String getOrderNo(String openid,String transType){
		Date date=new Date();
		StringBuffer buffer=new StringBuffer();
		buffer.append(MD5Utils.toMD5_16(openid));
		buffer.append(transType);
		buffer.append(yy_mm_ddFormat.format(date));
		buffer.append(date.getTime());
		return buffer.toString();
	}

}
