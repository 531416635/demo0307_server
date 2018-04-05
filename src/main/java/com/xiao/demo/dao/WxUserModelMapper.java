package com.xiao.demo.dao;

import com.xiao.demo.model.WxUserModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WxUserModelMapper {
    /**
     *
     * @mbg.generated 2018-03-25
     */
    int insert(WxUserModel record);

    /**
     *
     * @mbg.generated 2018-03-25
     */
    int insertSelective(WxUserModel record);

    /**
     *
     * @mbg.generated 2018-03-25
     */
    List<WxUserModel> getUserBySelect(WxUserModel record);
}