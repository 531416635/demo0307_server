package com.xiao.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

/**
 * HTTP请求
 * Description:
 * @author yaoyuxiao 
 * 创建时间：2017年4月9日 下午1:16:56
 */
public class HTTPUtils {
	
    private static Logger logger = LoggerFactory.getLogger( HTTPUtils.class );

	/**
	 * 发送get请求
	 *
	 * @param url
	 *            请求地址
	 * @param map
	 *            请求参数
	 *
	 * @return 请求结果
	 *
	 * @throws IOException
	 */
	public static String sendGet(String url, Map<String, String> map)
			throws IOException {
		logger.info("sendGet 请求的地址为{}",url);
		StringBuilder buffer = new StringBuilder(); // 用来拼接参数
		StringBuilder result = new StringBuilder(); // 用来接受返回值
		URL httpUrl; // HTTP URL类 用这个类来创建连接
		URLConnection connection; // 创建的http连接
		BufferedReader bufferedReader; // 接受连接受的参数
		// 如果存在参数，我们才需要拼接参数 类似于 localhost/index.html?a=a&b=b
		if (map.size() > 0) {
			Set<String> keys = map.keySet();
			for (String key : keys) {
				buffer.append(key)
				.append("=")
				.append(URLEncoder.encode(map.get(key),"utf-8")).append("&");
			}
			url = url + "?" + buffer.toString().substring(0,buffer.toString().length()-1);
		}
		// 创建URL
		httpUrl = new URL(url);
		// 建立连接
		connection = httpUrl.openConnection();
		connection.setRequestProperty("accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		connection.setRequestProperty("connection", "keep-alive");
		connection.setRequestProperty("user-agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
		connection.connect();
		// 接受连接返回参数
		bufferedReader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			result.append(line);
		}
		bufferedReader.close();
		logger.info("sendGet 请求返回的结果集为：{}",result.toString());
		return result.toString();
	}

	public static String sendPost(String url,  Map<String, String> map) throws IOException {
		logger.info("sendPost 请求的地址为{}",url);
		StringBuilder buffer = new StringBuilder(); // 用来拼接参数
		StringBuilder result = new StringBuilder(); // 用来接受返回值
		URL httpUrl; // HTTP URL类 用这个类来创建连接
		URLConnection connection; // 创建的http连接
		PrintWriter printWriter;
		BufferedReader bufferedReader; // 接受连接受的参数
		// 创建URL
		httpUrl = new URL(url);
		// 建立连接
		connection = httpUrl.openConnection();
		connection
				.setRequestProperty("accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		connection.setRequestProperty("connection", "keep-alive");
		connection
				.setRequestProperty("user-agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		printWriter = new PrintWriter(connection.getOutputStream());

		String param = buffer.toString();
		if (map.size() > 0) {
			Set<String> keys = map.keySet();
			for (String key : keys) {
				buffer.append(key)
				.append("=")
				.append(URLEncoder.encode(map.get(key),"utf-8")).append("&");
			}
			param = buffer.toString().substring(0,buffer.toString().length()-1);
		}
		
		printWriter.print(param);
		printWriter.flush();
		connection.connect();
		// 接受连接返回参数
		bufferedReader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			result.append(line);
		}
		bufferedReader.close();
		logger.info("sendPost 请求返回的结果集为：{}",result.toString());
		return result.toString();
	}
}