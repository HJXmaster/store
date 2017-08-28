package com.mall.service.impl;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.OrderInfoMapper;
import com.mall.dao.OrdersMapper;
import com.mall.dao.PreOrderMapper;
import com.mall.entity.OrderInfo;
import com.mall.entity.Orders;
import com.mall.entity.PreOrder;
import com.mall.service.OrdersService;
import com.mall.util.UObjects;
@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	OrdersMapper ordersMapper;
	@Autowired
	OrderInfoMapper orderInfoMapper;
	@Autowired
	PreOrderMapper preOrderMapper;
	
	@Override
	public boolean delete(Integer oid) {
		return (ordersMapper.deleteByPrimaryKey(oid)==1)?true:false;
	}

	@Override
	public boolean insert(Orders orders) {
		return (ordersMapper.insert(orders)==1)?true:false;
	}

	@Override
	public boolean insertBySelective(Orders orders) {
		return (ordersMapper.insertSelective(orders)==1)?true:false;
	}

	@Override
	public Orders select(Integer oid) {
		return ordersMapper.selectByPrimaryKey(oid);
	}

	@Override
	public boolean updateBySelective(Orders orders) {
		UObjects.transferEmptyToNullInObject(orders);
		return (ordersMapper.updateByPrimaryKeySelective(orders)==1)?true:false;
	}

	@Override
	public boolean update(Orders orders) {
		UObjects.transferEmptyToNullInObject(orders);
		return (ordersMapper.updateByPrimaryKey(orders)==1)?true:false;
	}

	@Override
	public List<Orders> getOrdersByUid(Integer uid) {
		return ordersMapper.getOrdersByUid(uid);
	}

	@Override
	public List<Orders> getOrdersByTime(Date startTime, Date endTime) {
		Map<String,Date> time=new HashMap<String,Date>();
		time.put("startTime", startTime);
		time.put("endTime", endTime);
		return ordersMapper.getOrdersByTime(time);
	}
	@Override
	public Map<String,Object> insertAuto(Orders orders,int cid,int csize) {

		Map<String,Object> map=new HashMap<String, Object>();
		boolean status=ordersMapper.insertAuto(orders);
		map.put("status", status);
		
		int oid=orders.getOid();
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setCid(cid);
		orderInfo.setCsize(csize);
		orderInfo.setOid(oid);
		orderInfoMapper.insert(orderInfo);
		map.put("oid", oid);
		System.out.println(oid);
		return map;
	}
	@Override
	public List<OrderInfo> getOrderInfosByTimeAndCid(Date startTime, Date endTime, int cid) {
		Map<String, Object> ordersMap=new HashMap<String, Object>();
		ordersMap.put("startTime", startTime);
		ordersMap.put("endTime", endTime);
		ordersMap.put("cid", cid);
		return orderInfoMapper.getOrderInfosByTimeAndCid(ordersMap);
	}
@Override
	public int insertAutoShoppingCart(Orders orders, int[] cidInts,
			int[] csizeInts) {
	Map<String,Object> map=new HashMap<String, Object>();
	boolean status=ordersMapper.insertAuto(orders);
	map.put("status", status);
		
		int oid=orders.getOid();
		for (int i = 0; i < csizeInts.length; i++) {
			OrderInfo orderInfo=new OrderInfo();
			orderInfo.setCid(cidInts[i]);
			orderInfo.setCsize(csizeInts[i]);
			orderInfo.setOid(oid);
			orderInfoMapper.insert(orderInfo);
		}
		map.put("oid", oid);
		
		return oid;
	}

@Override
public List<Orders> getOrders() {
	List<Orders> list=ordersMapper.selectAll();
	return list;
}
}
