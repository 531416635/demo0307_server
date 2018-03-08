package com.xiao.demo.controller.manager;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.config.Constant;
import com.xiao.demo.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 描述：菜单管理
 * 作者：yaoyuxiao
 * 时间：2018/3/8 21:56
 */
@RestController
@RequestMapping(value = "/menu")
public class MenuController {
    private  static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private Constant constant;

    @Autowired
    private MenuService menuService;

    /**
     * 获取菜单列表
     * @return
     */
    @RequestMapping(value = "/getAllMenu.do",produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject getAllMenu(@RequestBody(required = false) Map<String,Object> map){
        JSONObject json = new JSONObject();
        json.put("code","-1");
        try{
            json.put("code","1");
            json.put("result",menuService.selectAllMenus(map));
        }catch (Exception e){
            e.printStackTrace();
            json.put("code","-2");
            json.put("msg","获取菜单信息异常");
        }
        return json;
    }

}
