package com.xiao.demo.model;

import java.util.Date;

public class WxOrderModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String openid;

    /**
     * 
     */
    private String orderId;

    /**
     * 1、冰箱 2、洗衣机 3、换锁芯 4、电视 5、空调
     */
    private String type;

    /**
     * 冰箱 洗衣机 换锁芯 电视 空调
     */
    private String title;

    /**
     * 问题描述
     */
    private String question;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * gps获取的地理位置
     */
    private String gpsAddress;

    /**
     * 省市县行政区域
     */
    private String area;

    /**
     * 具体门牌号地址
     */
    private String address;

    /**
     * 订单状态
     */
    private String orderStatus;

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
     * 
     * @return openid 
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 
     * @param openid 
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * 
     * @return order_id 
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 
     * @param orderId 
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 1、冰箱 2、洗衣机 3、换锁芯 4、电视 5、空调
     * @return type 1、冰箱 2、洗衣机 3、换锁芯 4、电视 5、空调
     */
    public String getType() {
        return type;
    }

    /**
     * 1、冰箱 2、洗衣机 3、换锁芯 4、电视 5、空调
     * @param type 1、冰箱 2、洗衣机 3、换锁芯 4、电视 5、空调
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 冰箱 洗衣机 换锁芯 电视 空调
     * @return title 冰箱 洗衣机 换锁芯 电视 空调
     */
    public String getTitle() {
        return title;
    }

    /**
     * 冰箱 洗衣机 换锁芯 电视 空调
     * @param title 冰箱 洗衣机 换锁芯 电视 空调
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 问题描述
     * @return question 问题描述
     */
    public String getQuestion() {
        return question;
    }

    /**
     * 问题描述
     * @param question 问题描述
     */
    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    /**
     * 创建时间
     * @return createTime 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 手机号码
     * @return phone 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号码
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * gps获取的地理位置
     * @return gps_address gps获取的地理位置
     */
    public String getGpsAddress() {
        return gpsAddress;
    }

    /**
     * gps获取的地理位置
     * @param gpsAddress gps获取的地理位置
     */
    public void setGpsAddress(String gpsAddress) {
        this.gpsAddress = gpsAddress == null ? null : gpsAddress.trim();
    }

    /**
     * 省市县行政区域
     * @return area 省市县行政区域
     */
    public String getArea() {
        return area;
    }

    /**
     * 省市县行政区域
     * @param area 省市县行政区域
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * 具体门牌号地址
     * @return address 具体门牌号地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 具体门牌号地址
     * @param address 具体门牌号地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}