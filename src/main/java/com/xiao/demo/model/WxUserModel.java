package com.xiao.demo.model;

import java.util.Date;

public class WxUserModel {
    /**
     * 用户的唯一标识
     */
    private String openid;

    /**
     * 
     */
    private String scope;

    /**
     * 
     */
    private String expiresIn;

    /**
     * 
     */
    private String refreshToken;

    /**
     * 
     */
    private String accessToken;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private String sex;

    /**
     * 用户个人资料填写的省份
     */
    private String province;

    /**
     * 普通用户个人资料填写的城市
     */
    private String city;

    /**
     * 国家，如中国为CN
     */
    private String country;

    /**
     * 
     */
    private String headimgurl;

    /**
     * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom
     */
    private String privilege;

    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    private String unionid;

    /**
     * 用户的唯一标识
     * @return openid 用户的唯一标识
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 用户的唯一标识
     * @param openid 用户的唯一标识
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * 
     * @return scope 
     */
    public String getScope() {
        return scope;
    }

    /**
     * 
     * @param scope 
     */
    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    /**
     * 
     * @return expires_in 
     */
    public String getExpiresIn() {
        return expiresIn;
    }

    /**
     * 
     * @param expiresIn 
     */
    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn == null ? null : expiresIn.trim();
    }

    /**
     * 
     * @return refresh_token 
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * 
     * @param refreshToken 
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken == null ? null : refreshToken.trim();
    }

    /**
     * 
     * @return access_token 
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * 
     * @param accessToken 
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    /**
     * 
     * @return create_time 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     * @return update_time 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     * @param updateTime 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 用户昵称
     * @return nickname 用户昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 用户昵称
     * @param nickname 用户昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     * @return sex 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    public String getSex() {
        return sex;
    }

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     * @param sex 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 用户个人资料填写的省份
     * @return province 用户个人资料填写的省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 用户个人资料填写的省份
     * @param province 用户个人资料填写的省份
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 普通用户个人资料填写的城市
     * @return city 普通用户个人资料填写的城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 普通用户个人资料填写的城市
     * @param city 普通用户个人资料填写的城市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 国家，如中国为CN
     * @return country 国家，如中国为CN
     */
    public String getCountry() {
        return country;
    }

    /**
     * 国家，如中国为CN
     * @param country 国家，如中国为CN
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * 
     * @return headimgurl 
     */
    public String getHeadimgurl() {
        return headimgurl;
    }

    /**
     * 
     * @param headimgurl 
     */
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    /**
     * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom
     * @return privilege 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom
     */
    public String getPrivilege() {
        return privilege;
    }

    /**
     * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom
     * @param privilege 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom
     */
    public void setPrivilege(String privilege) {
        this.privilege = privilege == null ? null : privilege.trim();
    }

    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     * @return unionid 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    public String getUnionid() {
        return unionid;
    }

    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     * @param unionid 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }
}