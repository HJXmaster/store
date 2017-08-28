package com.mall.controller.wr;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.entity.OrderInfo;
import com.mall.entity.Orders;
import com.mall.entity.User;
import com.mall.service.OrdersService;
import com.mall.service.UserService;
import com.mall.util.UObjects;
import com.mall.util.wx.DateUtil;

@Controller
public class OrdersManagerController {
	@Autowired
	OrdersService ordersService;
	@Autowired
	UserService userService;

	public OrdersService getOrdersService() {
		return ordersService;
	}

	public void setOrdersService(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

	@ResponseBody
	@RequestMapping("/queryOrdersByTime.do")
	public List<Orders> queryOrdersByTime(String startTime, String endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(startTime + " " + endTime);
		List<Orders> list = null;
		try {
			list = ordersService.getOrdersByTime(sdf.parse(startTime), sdf.parse(endTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@ResponseBody
	@RequestMapping("/queryAllOrders.do")
	public List<Orders> queryOrders() {

		List<Orders> list = ordersService.getOrders();
		for (Orders o : list) {
			User user = userService.getByUId(o.getUid());
			if (user != null) {
				if (!UObjects.isNonNullEmpty(o.getOname()))
					o.setOname(user.getUname());
				if (!UObjects.isNonNullEmpty(o.getOaddress()))
					o.setOaddress(user.getUaddress());
				if (!UObjects.isNonNullEmpty(o.getOpostcode()))
					o.setOpostcode(user.getUpostcode());
				if (!UObjects.isNonNullEmpty(o.getOphone()))
					o.setOphone(user.getUphone());
			}
		}
		return list;
	}

	@ResponseBody
	@RequestMapping("/countCommoditySales.do")
	public Map<String, Object> countCommoditySales(String startTime, String endTime, Integer cid) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(startTime + "  " + endTime);
		List<OrderInfo> list = null;
		int count = 0;
		try {
			list = ordersService.getOrderInfosByTimeAndCid(sdf.parse(startTime), sdf.parse(endTime), cid);
			for (OrderInfo info : list) {
				count += info.getCsize();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map map = new HashMap<String, Object>();
		map.put("count", count);
		return map;
	}

	/*
	 * 参数：cid
	 * 
	 */

	@ResponseBody
	@RequestMapping("/countMonthCommoditySales.do")
	public Map<Integer, Map<String,String>> countYearCommoditySales(Integer cid) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DateUtil dateUtil = new DateUtil();
		List<String[]> dateList = dateUtil.getNearlyYear();

		String[] dateNameArr = (String[]) dateList.get(0);
		String[] concatDateArr = (String[]) dateList.get(1);

		Map<String, String> msg1Map = new LinkedHashMap<String, String>();
		Map<String, String> msg2Map = new LinkedHashMap<String, String>();
		List<OrderInfo> list = null;

		Map<Integer, Map<String, String>> finalMap = null;
		try {
			for (int i = 10; i >= 0; i--) {
				int count = 0;
				list = ordersService.getOrderInfosByTimeAndCid(sdf.parse(concatDateArr[i+1]),
						sdf.parse(concatDateArr[i]), cid);
				for (OrderInfo info : list) {
					count += info.getCsize();
				}
				msg1Map.put(String.valueOf(10 - i), dateNameArr[i+1]);
				msg2Map.put(String.valueOf(10 - i), String.valueOf(count));
			}
		 finalMap = new HashMap<Integer, Map<String,String>>();
			finalMap.put(0, msg1Map);
			finalMap.put(1, msg2Map);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return finalMap;
	}

	/**
	 * 取消订单，不接单
	 * 
	 * @param oid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteOrdersById.do")
	public Map deleteOrdersById(Integer oid) {
		Map map = new HashMap<String, Object>();
		Orders order = ordersService.select(oid);
		if (order != null) {
			order.setOstate("已取消");
			ordersService.updateBySelective(order);
			map.put("status", true);
			map.put("msg", "取消订单成功");
		} else {
			map.put("status", false);
			map.put("msg", "查无此订单");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/deleteOrders.do")
	public Map deleteOrders(HttpSession session,Integer oid) {
		Map map = new HashMap<String, Object>();
		Integer uid=(Integer)session.getAttribute("uid");
		if(uid==null){
			map.put("status", false);
			map.put("msg", "未登录");
		}
		Orders order = ordersService.select(oid);
		if (order != null) {
			order.setOstate("已取消");
			ordersService.updateBySelective(order);
			map.put("status", true);
			map.put("msg", "取消订单成功");
		} else {
			map.put("status", false);
			map.put("msg", "查无此订单");
		}
		return map;
	}

	/**
	 * 更新订单
	 */
	@ResponseBody
	@RequestMapping("updateOrders.do")
	public Map<String,Object> update(Orders orders,String ostimes,String oetimes) {
		
		orders.setOstime(UObjects.parse(ostimes));
		orders.setOetime(UObjects.parse(oetimes));
		Map<String, Object> map = new HashMap<String, Object>();
		
		boolean up=ordersService.updateBySelective(orders);
		if (up == true) {
			map.put("status", true);
			map.put("msg", "订单更新成功");
		} else {
			map.put("status", false);
			map.put("msg", "订单更新失败");
		}
		return map;
		
	}
}
