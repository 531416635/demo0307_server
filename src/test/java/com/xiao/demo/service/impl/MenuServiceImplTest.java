package com.xiao.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.dao.MenuModelMapper;
import com.xiao.demo.model.MenuModel;
import com.xiao.demo.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 描述：
 * 作者：yaoyuxiao
 * 时间：2018/3/10 14:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImplTest.class);

    @Autowired
    MenuModelMapper menuDao;

    @Test
    public void selectAllMenus() {

    }


}