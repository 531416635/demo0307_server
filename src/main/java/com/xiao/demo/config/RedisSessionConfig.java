package com.xiao.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 描述：redis实现session共享
 * 作者： yaoyuxiao
 * 创建时间： 2018/2/8  10:02
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
}
