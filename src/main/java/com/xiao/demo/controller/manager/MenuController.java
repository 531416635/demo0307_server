package com.xiao.demo.controller.manager;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.config.Constant;
import com.xiao.demo.model.MenuModel;
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

    /**
     * 获取父子菜单列表
     * @return
     */
    @RequestMapping(value = "/getMenuChildren.do",produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject getMenuChildren(@RequestBody(required = false) Map<String,Object> map){
        JSONObject json = new JSONObject();
        json.put("code","-1");
        try{
            json.put("code","1");
            json.put("result",menuService.getMenuChildren(map));
        }catch (Exception e){
            e.printStackTrace();
            json.put("code","-2");
            json.put("msg","获取父子菜单列表异常");
        }
        return json;
    }


    /**
     * 插入菜单
     * @return
     */
    @RequestMapping(value = "/addMenu.do",produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject addMenu(@RequestBody(required = false)MenuModel menuModel){
        JSONObject json = new JSONObject();
        json.put("code","-1");
        try{
            json.put("code","1");
            json.put("result",menuService.insertSelective(menuModel));
        }catch (Exception e){
            e.printStackTrace();
            json.put("code","-2");
            json.put("msg","插入菜单异常");
        }
        return json;
    }

    /**
     * 更新菜单
     * @return
     */
    @RequestMapping(value = "/editMenu.do",produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject editMenu(@RequestBody(required = false)MenuModel menuModel){
        JSONObject json = new JSONObject();
        json.put("code","-1");
        try{
            json.put("code","1");
            json.put("result",menuService.updateByPrimaryKeySelective(menuModel));
        }catch (Exception e){
            e.printStackTrace();
            json.put("code","-2");
            json.put("msg","更新菜单异常");
        }
        return json;
    }
}
