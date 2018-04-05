package com.xiao.demo.config;

import java.util.Properties;

/**
 * 配置文件
 *
 * @author yaoyuxiao
 * 创建于 2018年3月25日 下午4:48:17
 */
public class ConfUtil {

    private static Properties properties = new Properties();

    static {
        try {
            // 加载配置文件
            properties.load(ConfUtil.class.getClassLoader().getResourceAsStream("config/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 开发者ID(AppID)
     */
    public static String getAppID() {
        return properties.getProperty("AppID");
    }


    /**
     * 第三方用户唯一凭证密钥，即appsecret
     */
    public static String getAppSecret() {
        return properties.getProperty("AppSecret");
    }

    /**
     * 微信首页地址
     */
    public static String getDemouiIndex() {
        return properties.getProperty("demouiIndex");
    }

    /**
     * 授权地址
     */
    public static String getAuthUrl() {
        return properties.getProperty("authUrl");
    }
}
