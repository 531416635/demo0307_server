package com.xiao.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ServletComponentScan 设置启动时spring能够扫描到我们自己编写的servlet和filter,
 * 用于Druid监控
 * @EnableTransactionManagement 启用事物  需配合@Transactional使用
 * @MapperScan 用于扫描的mapper接口
 */
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("com.xiao.demo.dao") //配置扫描mapper接口的地址
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
