package com.mall.controller.jx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.entity.Commodity;
import com.mall.entity.OrderInfo;
import com.mall.entity.Orders;
import com.mall.entity.User;
import com.mall.service.CommodityService;
import com.mall.service.OrdersService;
import com.mall.service.UserService;

@Controller
public class OrdersController {
	@Autowired
	OrdersService ordersService;
	@Autowired
	UserService userService;
	@Autowired
	CommodityService commodityService;
	/**
	 * 获得用户默认邮递信息
	 * @param session
	 * @return json
	 */
	@ResponseBody
	@RequestMapping("getAddress.do")
	public Map getAddress(HttpSession session){
		Map map=new HashMap<String, Object>();
		Integer uid=(Integer)session.getAttribute("uid");
		if(uid==null){
			map.put("status", false);
			map.put("mag", "未登录");
		}
		User user=userService.getByUId(uid);
		
		map.put("name", user.getUname());
		map.put("address", user.getUaddress());
		map.put("phone", user.getUphone());
		map.put("postcode", user.getUpostcode());
		return map;
	}
	/**
	 * 修改邮递信息
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("pushAddress.do")
	public Map pushAddress(HttpSession session){
		Integer uid=(Integer)session.getAttribute("uid");
		User user=userService.getByUId(uid);
		Map map=new HashMap<String, Object>();
		map.put("name", user.getUname());
		map.put("address", user.getUaddress());
		map.put("phone", user.getUphone());
		map.put("postcode", user.getUpostcode());
		return map;
	}
	@ResponseBody
	@RequestMapping("queryOrder.do")
	public Orders queryOrder(Integer oid){
		Orders order=ordersService.select(oid);
		return order;
	}
	
	@ResponseBody
	@RequestMapping("queryOrderMySelf.do")
	public Object queryOrderByMySelf(HttpSession session,Integer oid){
		Map map=new HashMap<String, Object>();
		Integer uid=(Integer)session.getAttribute("uid");
		if(uid==null){
			map.put("status", false);
			map.put("mag", "未登录");
			return map;
		}
		Orders order=ordersService.select(oid);
		if(order!=null){
			return order;
		}
		else {
			map.put("status", false);
			map.put("mag", "找不到订单");
			return map;
		}
	}
	
	@ResponseBody
	@RequestMapping("/confirmReceipt.do")
	public Map<String,Object> confirmReceipt(HttpSession session,Integer oid){
		Map<String,Object> map=new HashMap<String, Object>();
		Integer uid=(Integer)session.getAttribute("uid");
		if(uid==null){
			map.put("status", false);
			map.put("mag", "未登录");
			return map;
		}
		Orders order=ordersService.select(oid);
		if(order!=null&&order.getUid().equals(uid)){
			if(order.getOetime()!=null){
				map.put("status", false);
				map.put("msg", "你已经确认收货");
				return map;
			}
			order.setOetime(new Date());
			order.setOstate("已收货");
			map.put("status", ordersService.updateBySelective(order));
			map.put("msg", "确认收货成功");
			return map;
		}
		map.put("status", false);
		map.put("msg", "找不到订单");
		return map;
	}
	
}
