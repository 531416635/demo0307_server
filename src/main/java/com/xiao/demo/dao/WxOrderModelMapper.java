package com.xiao.demo.dao;

import com.xiao.demo.model.WxOrderModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WxOrderModelMapper {
    /**
     *
     * @mbg.generated 2018-04-08
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-04-08
     */
    int insert(WxOrderModel record);

    /**
     *
     * @mbg.generated 2018-04-08
     */
    int insertSelective(WxOrderModel record);

    /**
     *
     * @mbg.generated 2018-04-08
     */
    WxOrderModel selectByPrimaryKey(Integer id);


    int selectCountByOrderStatus(Map<String, String> map);


    List<WxOrderModel> selectOrderByPage(Map<String, Object> map);

    /**
     *
     * @mbg.generated 2018-04-08
     */
    int updateByPrimaryKeySelective(WxOrderModel record);

    /**
     *
     * @mbg.generated 2018-04-08
     */
    int updateByPrimaryKey(WxOrderModel record);
}