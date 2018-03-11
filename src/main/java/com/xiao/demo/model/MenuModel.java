package com.xiao.demo.model;

import java.util.List;

public class MenuModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 访问路径
     */
    private String menuPath;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单类型
     */
    private String menuType;

    /**
     * 父菜单
     */
    private Integer menuParent;

    /**
     * 是否需要权限
     */
    private Boolean menuAuth;

    /**
     * 菜单是否可用
     */
    private Boolean menuEnabled;

    //tree 子树为节点
    private List<MenuModel> children;

    public List<MenuModel> getChildren() {
        return children;
    }

    public void setChildren(List<MenuModel> children) {
        this.children = children;
    }

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 访问路径
     * @return menu_path 访问路径
     */
    public String getMenuPath() {
        return menuPath;
    }

    /**
     * 访问路径
     * @param menuPath 访问路径
     */
    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath == null ? null : menuPath.trim();
    }

    /**
     * 菜单名称
     * @return menu_name 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 菜单名称
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * 菜单类型
     * @return menu_type 菜单类型
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * 菜单类型
     * @param menuType 菜单类型
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }

    /**
     * 父菜单
     * @return menu_parent 父菜单
     */
    public Integer getMenuParent() {
        return menuParent;
    }

    /**
     * 父菜单
     * @param menuParent 父菜单
     */
    public void setMenuParent(Integer menuParent) {
        this.menuParent = menuParent;
    }

    /**
     * 是否需要权限
     * @return menu_auth 是否需要权限
     */
    public Boolean getMenuAuth() {
        return menuAuth;
    }

    /**
     * 是否需要权限
     * @param menuAuth 是否需要权限
     */
    public void setMenuAuth(Boolean menuAuth) {
        this.menuAuth = menuAuth;
    }

    /**
     * 菜单是否可用
     * @return menu_enabled 菜单是否可用
     */
    public Boolean getMenuEnabled() {
        return menuEnabled;
    }

    /**
     * 菜单是否可用
     * @param menuEnabled 菜单是否可用
     */
    public void setMenuEnabled(Boolean menuEnabled) {
        this.menuEnabled = menuEnabled;
    }
}