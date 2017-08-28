package com.mall.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.entity.Orders;
@Repository
public interface OrdersMapper {
    int deleteByPrimaryKey(Integer oid);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer oid);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
    
    List<Orders> getOrdersByUid(Integer uid); 
    List<Orders> getOrdersByTime(Map<String,Date> time);
    

	boolean insertAuto(Orders orders);

	List<Orders> selectAll(); 
}