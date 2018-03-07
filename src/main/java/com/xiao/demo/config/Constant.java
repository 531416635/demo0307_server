package com.xiao.demo.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 描述：系统中使用到的常量配置文件
 * 作者： yaoyuxiao
 * 创建时间： 2018/2/8  9:34
 */
@Component
@ConfigurationProperties(ignoreUnknownFields = false,prefix = "constant")
@PropertySource("classpath:/config/Constant.properties")
public class Constant {

    public  String name;
    public int expireTime;

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
