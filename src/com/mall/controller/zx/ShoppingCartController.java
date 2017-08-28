package com.mall.controller.zx;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Order;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.serializer.URLCodec;
import com.mall.dao.UserMapper;
import com.mall.entity.Commodity;
import com.mall.entity.OrderInfo;
import com.mall.entity.Orders;
import com.mall.entity.PreOrder;
import com.mall.entity.ShoppingCart;
import com.mall.entity.User;
import com.mall.service.CommodityService;
import com.mall.service.ShoppingCartService;
import com.mall.service.UserService;
import com.mall.util.ShoppingCartUtil;
import com.mall.util.UObjects;
import com.mysql.fabric.Response;

@org.springframework.stereotype.Controller
public class ShoppingCartController {
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private CommodityService commodityService;
	@Autowired
	private UserService userService;
	
	// 10.添加商品到购物车
	@RequestMapping("addCart.do")
	public @ResponseBody
	Map<String, Object> addCart(HttpServletResponse response,HttpServletRequest request,HttpSession seesion, Integer cid, Integer csize) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer uid = (Integer) seesion.getAttribute("uid");
		if (uid == null) {
//			map.put("status", false);
//			map.put("msg", "请先登录");
			ShoppingCart shoppingCart=ShoppingCartUtil.getShoppingCart(request);
			if(cid!=null&&csize!=null){
				PreOrder item=new PreOrder();
				item.setCid(cid);
				Commodity c=commodityService.select(cid);
				item.setCommodity(c);
				item.setCsize(csize);
				if(csize>c.getCremain()||!shoppingCart.addItem(item)){
					map.put("status", false);
					map.put("msg", "库存不足,商品只剩 " + c.getCremain() + " 件");
					return map;
				}
				
			}
			ShoppingCartUtil.writeCookie(response, shoppingCart);
			map.put("status", true);
			return map;
		}

		PreOrder preOrder = new PreOrder();
		preOrder.setUid(uid);
		preOrder.setCid(cid);
		preOrder.setCsize(csize);
		Commodity c = commodityService.select(cid);
		if (c == null) {
			map.put("status", false);
			map.put("msg", "商品已下架");
			return map;
		}
		if (csize == null) {
			preOrder.setCsize(0);
		} else if (shoppingCartService.queryByUC(preOrder) == null) {
			if (csize < c.getCremain())
				preOrder.setCsize(csize);
			else {
				map.put("status", false);
				map.put("msg", "库存不足,商品只剩 " + c.getCremain() + " 件");
				return map;
			}
		} else if (shoppingCartService.queryByUC(preOrder).getCsize() + csize > c
				.getCremain()) {
			preOrder.setCsize(c.getCremain());
			boolean value = shoppingCartService.updateOne(preOrder);
			map.put("status", false);
			map.put("msg", "库存不足,商品只剩 " + c.getCremain() + " 件");
			return map;
		}

		boolean value = shoppingCartService.insertOne(preOrder);
		map.put("status", value);
		return map;
	}

	// 11.查询购物车商品
	@RequestMapping("queryCart.do")
	public @ResponseBody
	List<PreOrder> queryCart(HttpServletRequest request,HttpSession seesion) {
		Integer uid = (Integer) seesion.getAttribute("uid");
		System.out.println("用户id" + uid);
		if(uid!=null){
		List<PreOrder> pOrders = shoppingCartService.selectCart(uid);
//		for (PreOrder p : pOrders) {
//			System.out.println(p);
//		}
		return shoppingCartService.selectCart(uid);
		}
		
		ShoppingCart shoppingCart=ShoppingCartUtil.getShoppingCart(request);

		if(shoppingCart!=null){
//			for(PreOrder item:shoppingCart.getItems()){
//				System.out.println(item.getCommodity().getCname()+item);
//			}
			return shoppingCart.getItems();
		}
		else return null;
	}

	// 12.删除购物车商品
	@RequestMapping("deleteCart.do")
	public @ResponseBody
	Map<String, Object> deleteCart(HttpServletResponse response,HttpServletRequest request,HttpSession seesion, int cid) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer uid = (Integer) seesion.getAttribute("uid");
		if (uid == null) {
//			map.put("status", false);
//			map.put("msg", "未登录");
			ShoppingCart shoppingCart=ShoppingCartUtil.getShoppingCart(request);
			shoppingCart.delete(cid);
			ShoppingCartUtil.writeCookie(response, shoppingCart);
			map.put("status", true);
		} else {
			PreOrder preOrder = new PreOrder();
			preOrder.setCid(cid);
			preOrder.setUid(uid);

			if (shoppingCartService.deleteOne(preOrder)) {
				map.put("status", true);
			} else {
				map.put("status", false);
				map.put("msg", "请检查该商品是否存在！");
			}
		}
		return map;
	}

	// 13.修改购物车商品
	@RequestMapping("updateCart.do")
	public @ResponseBody
	Map<String, Object> updateCart(HttpServletRequest request,HttpServletResponse response,HttpSession seesion, Integer cid,
			Integer csize) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer uid = (Integer) seesion.getAttribute("uid");
		if (uid == null) {
//			map.put("status", false);
//			map.put("msg", "未登录");
			ShoppingCart shoppingCart=ShoppingCartUtil.getShoppingCart(request);
			if(cid!=null&&csize!=null){
				PreOrder item=new PreOrder();
				item.setCid(cid);
				Commodity c=commodityService.select(cid);
				item.setCommodity(c);
				item.setCsize(csize);
				if(csize>c.getCremain()){
					map.put("status", false);
					map.put("msg", "库存不足,商品只剩 " + c.getCremain() + " 件");
					return map;
				}
				shoppingCart.updateItem(item);
			}
			ShoppingCartUtil.writeCookie(response, shoppingCart);
			map.put("status", true);
			return map;
		}
		PreOrder preOrder = new PreOrder();
		preOrder.setCid(cid);
		preOrder.setCsize(csize);
		preOrder.setUid(uid);
		Commodity c = commodityService.select(cid);
		if (c == null) {
			map.put("status", false);
			map.put("msg", "请检查该商品是否存在！");
			return map;
		}
		if (csize == null) {
			preOrder.setCsize(0);
		} else if (csize > c.getCremain()) {
			preOrder.setCsize(c.getCremain());
			map.put("status", false);
			map.put("msg", "库存不足,商品只剩 " + c.getCremain() + " 件");
			return map;
		}
		if (shoppingCartService.updateOne(preOrder)) {
			map.put("status", true);
		} else {
			map.put("status", false);
			map.put("msg", "请检查该商品是否存在！");
		}
		return map;
	}

	/**
	 * 购物车结算，返回即将生成的订单详情
	 * 
	 * @param seesion
	 * @param cids
	 * @param csizes
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/account.do")
	public Object account(HttpSession seesion, String cids, String csizes) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer uid = (Integer) seesion.getAttribute("uid");
		// 检测登录
		if (uid == null) {
			map.put("status", false);
			map.put("msg", "未登录");
			return map;
		}
		User user = userService.getByUId(uid);
		// 分割参数
		String[] cidStrings = null;
		String[] csizeStrings = null;
		cidStrings = cids.split("_");
		csizeStrings = csizes.split("_");

		// 存储至整形数组
		int[] cidInts = new int[cidStrings.length];
		for (int i = 0; i < cidInts.length; i++) {
			cidInts[i] = Integer.parseInt(cidStrings[i]);
		}
		csizeStrings = csizes.split("_");
		int[] csizeInts = new int[cidStrings.length];
		for (int i = 0; i < cidInts.length; i++) {
			csizeInts[i] = Integer.parseInt(csizeStrings[i]);
		}
		// 生成订单详情

		List<Map<String, Object>> infos = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < cidInts.length; i++) {
			Commodity c = commodityService.select(cidInts[i]);
			if (c != null && c.getCremain() < csizeInts[i]) {
				map.put("status", false);
				map.put("msg", "商品 " + c.getCname() + " 库存不足");
				return map;
			}
			Map<String, Object> info = new HashMap<String, Object>();
			info.put("img", c.getMiniPic());
			info.put("cid", cidInts[i]);
			info.put("cname", c.getCname());
			info.put("csize", csizeInts[i]);
			info.put("cprice", c.getCprice());
			info.put("sum", c.getCprice() * csizeInts[i]);
			infos.add(info);
		}
		map.put("infos", infos);

		map.put("uid", uid);

		// 计算总值
		double sumPrice = 0;
		for (int i = 0; i < cidInts.length; i++) {
			double singlePrice = commodityService.select(cidInts[i])
					.getCprice();
			sumPrice += singlePrice * csizeInts[i];
		}
		map.put("total", sumPrice);

		// 加入默认邮递信息
		map.put("address", user.getUaddress());
		map.put("postcode", user.getUpostcode());

		map.put("name", user.getUname());
		map.put("phone", user.getUphone());
		map.put("status", true);
		return map;
	}
}
