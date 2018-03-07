package com.xiao.demo.model;

public class RoleModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色代码
     */
    private String roleCode;

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
     * 角色名称
     * @return role_name 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 角色名称
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 角色代码
     * @return role_code 角色代码
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 角色代码
     * @param roleCode 角色代码
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }
}