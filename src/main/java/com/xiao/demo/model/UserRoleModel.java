package com.xiao.demo.model;

public class UserRoleModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer uId;

    /**
     * 角色ID
     */
    private Integer rId;

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
     * 用户ID
     * @return u_id 用户ID
     */
    public Integer getuId() {
        return uId;
    }

    /**
     * 用户ID
     * @param uId 用户ID
     */
    public void setuId(Integer uId) {
        this.uId = uId;
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
}