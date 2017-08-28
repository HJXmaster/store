package com.mall.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.entity.OrderInfo;
import com.mall.entity.Orders;
@Repository
public interface OrdersService {
	/**
	 * 删除订单
	 * @param oid 订单id
	 * @return true false
	 */
    boolean delete(Integer oid);
    /**
     * 插入订单
     * @param orders
     * @return true false
     */
    boolean insert(Orders orders);
    /**
     * 直接购买插入订单(自动生成主键)
     * @param orders cid csize
     * @return 狀態，對象
     */
    Map<String,Object> insertAuto(Orders orders,int cid,int csize);
    /**
     * 动态插入订单
     * @param orders
     * @return true false
     */
    boolean insertBySelective(Orders orders);
    /**
     * 查询订单
     * @param oid 订单id
     * @return orders 订单信息
     */
    Orders select(Integer oid);
    /**
     * 动态更新订单
     * @param orders 订单信息
     * @return true false
     */
    boolean updateBySelective(Orders orders);
    /**
     * 更新订单
     * @param orders 订单信息
     * @return true false
     */
    boolean update(Orders orders);
    /**
     * 根据用户查询订单
     * @param uid 用户id
     * @return list
     */
    List<Orders> getOrdersByUid(Integer uid); 
    /**
     * 查询所有订单
     * @return
     */
    List<Orders> getOrders(); 
    /**
     * 根据时间查询订单
     * @param startTime 开始时间点
     * @param endTime 结束时间点
     * @return List orders
     */
    List<Orders> getOrdersByTime(Date startTime,Date endTime);
    /**
     * 根据时间和商品编号查询订单
     * @param startTime 开始时间点
     * @param endTime 结束时间点
     * @return List orders
     */
    List<OrderInfo> getOrderInfosByTimeAndCid(Date startTime,Date endTime,int cid);
	/**
     * 购物车插入订单(自动生成主键)
     * @param orders cidInts csizeInts
     * @return oid
     */
	int insertAutoShoppingCart(Orders orders, int[] cidInts, int[] csizeInts); 
}