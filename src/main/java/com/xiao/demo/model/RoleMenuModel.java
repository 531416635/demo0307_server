package com.xiao.demo.model;

public class RoleMenuModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 角色ID
     */
    private Integer rId;

    /**
     * 菜单ID
     */
    private Integer mId;

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
     * 角色ID
     * @return r_id 角色ID
     */
    public Integer getrId() {
        return rId;
    }

    /**
     * 角色ID
     * @param rId 角色ID
     */
    public void setrId(Integer rId) {
        this.rId = rId;
    }

    /**
     * 菜单ID
     * @return m_id 菜单ID
     */
    public Integer getmId() {
        return mId;
    }

    /**
     * 菜单ID
     * @param mId 菜单ID
     */
    public void setmId(Integer mId) {
        this.mId = mId;
    }
}