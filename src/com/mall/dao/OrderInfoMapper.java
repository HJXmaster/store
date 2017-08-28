package com.mall.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.entity.OrderInfo;
import com.mall.entity.Orders;
@Repository
public interface OrderInfoMapper {
    int deleteByPrimaryKey(Integer iid);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer iid);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);
    List<OrderInfo> getOrderInfosByTimeAndCid(Map<String,Object> map);
}