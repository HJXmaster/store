package com.mall.controller.zx;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.entity.Commodity;
import com.mall.entity.OrderInfo;
import com.mall.entity.Orders;
import com.mall.entity.PreOrder;
import com.mall.entity.User;
import com.mall.service.CommodityService;
import com.mall.service.OrdersService;
import com.mall.service.ShoppingCartService;
import com.mall.service.UserService;
import com.mall.service.impl.CommodityServiceImpl;
import com.mall.util.UObjects;

@Controller
public class CzxOrdersController {

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private CommodityService commodityService;
	
	@Autowired
	private UserService userService;
	

	// 8、购买商品
	@RequestMapping("shopping.do")
	public @ResponseBody
	Map<String, Object> shopping(HttpSession seesion, int cid, int csize,
			String oaddress, @RequestParam(required = false) String opostcode,
			@RequestParam(required = false) String oname,
			@RequestParam(required = false) String ophone) {
		Map<String, Object> map = new HashMap<String, Object>();
		Commodity c=commodityService.select(cid);
		if(c.getCremain()<csize){
			map.put("status", false);
			map.put("msg", "商品 "+c.getCname()+" 库存不足");
			return map;
		}
		System.out.println(oaddress);
		Integer uid = (Integer) seesion.getAttribute("uid");
		if (uid == null) {
			map.put("status", false);
			map.put("msg", "未登录");
			return map;
		}
		User user=userService.getByUId(uid);
		// 生成订单
		Orders orders = new Orders();
		orders.setUid(uid);
		if(UObjects.isNonNullEmpty(oaddress)){
			orders.setOaddress(oaddress);
		}else orders.setOaddress(user.getUaddress());
		
		orders.setOstime(new Date());
		double singlePrice = commodityService.select(cid).getCprice();
		orders.setOtotalprice(singlePrice * csize);
		orders.setOstate("已支付");
		if (UObjects.isNonNullEmpty(opostcode)) {
			orders.setOpostcode(opostcode);
		}else{ 
			orders.setOpostcode(user.getUpostcode());
		}
		if (UObjects.isNonNullEmpty(oname)) {
			orders.setOname(oname);
		}else orders.setOname(user.getUname());
		if (UObjects.isNonNullEmpty(ophone)) {
			orders.setOphone(ophone);
		}else orders.setOphone(user.getUphone());

		
		
//		//库存相应减少csize件，改用触发器实现
//		c.setCremain(c.getCremain()-csize);
//		commodityService.updateBySelective(c);
		Map<String, Object> resultmap= ordersService.insertAuto(orders, cid, csize);
		map.put("status", resultmap.get("status"));
		map.put("oid", resultmap.get("oid"));
		
		return resultmap;
	}

	// 16、查询个人所有订单
	@RequestMapping("queryOrders.do")
	public @ResponseBody
	Object queryOrders(HttpSession seesion) {
		Integer uid = (Integer) seesion.getAttribute("uid");
		if (uid == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", false);
			map.put("msg", "未登录");
			return map;
		}
		
		
		return ordersService.getOrdersByUid(uid);
	}

//	// 17、查询单个订单详情
//	@RequestMapping("queryOrder.do")
//	public @ResponseBody
//	Orders queryOrder(HttpSession seesion, int oid) {
//		// 是否需要管理员身份？
//		return ordersService.select(oid);
//	}

	// 18、提交个人订单
	@RequestMapping("pushOrder.do")
	public @ResponseBody
	Map<String, Object> pushOrder(HttpSession seesion, String cids,
			String csizes, String oaddress,
			@RequestParam(required = false) String opostcode,
			@RequestParam(required = false) String oname,
			@RequestParam(required = false) String ophone) {

		Integer uid = (Integer) seesion.getAttribute("uid");

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
		Map<String, Object> map = new HashMap<String, Object>();
		for(int i=0;i<cidInts.length;i++){
			Commodity c=commodityService.select(cidInts[i]);
			if(c!=null&&c.getCremain()<csizeInts[i]){
				map.put("status", false);
				map.put("msg", "商品 "+c.getCname()+" 库存不足");
				return map;
			}
		}

		// 生成订单
		Orders orders = new Orders();
		orders.setUid(uid);
		orders.setOaddress(oaddress);
		orders.setOstime(new Date());

		// 计算总值
		double sumPrice = 0;
		for (int i = 0; i < cidInts.length; i++) {
			double singlePrice = commodityService.select(cidInts[i])
					.getCprice();
			sumPrice += singlePrice * csizeInts[i];
		}
		orders.setOtotalprice(sumPrice);

		orders.setOstate("已支付");

		// 加入附加信息
		if (opostcode != null) {
			orders.setOpostcode(opostcode);
		}
		if (oname != null) {
			orders.setOname(oname);
		}
		if (ophone != null) {
			orders.setOphone(ophone);
		}

		// 检测登录
		
		if (uid == null) {
			map.put("status", false);
			map.put("msg", "未登录");
			return map;
		}

		// 插入订单同时添加记录
		int oid = ordersService.insertAutoShoppingCart(orders, cidInts,
				csizeInts);

		// 修改购物车
		List<PreOrder> preOrders = new ArrayList<PreOrder>();
		for (int i = 0; i < csizeStrings.length; i++) {
			PreOrder preOrder = new PreOrder();
			preOrder.setUid(uid);
			preOrder.setCid(Integer.valueOf(cidStrings[i]));
			preOrder.setCsize(Integer.valueOf(csizeStrings[i]));
			preOrders.add(preOrder);
		}
		for (PreOrder preOrder : preOrders) {
			PreOrder oldPreOrder = shoppingCartService.queryByUC(preOrder);
			// 判断减至数目为零时删除，否则更新即可
			int rest = oldPreOrder.getCsize() - preOrder.getCsize();
			if (rest == 0) {
				shoppingCartService.deleteOne(oldPreOrder);
			} else {
				preOrder.setCsize(rest);
				shoppingCartService.updateOne(preOrder);
			}

		}

		Orders newOrders = ordersService.select(oid);
		map.put("status", true);
		map.put("oid", newOrders.getOid());
		map.put("oaddress", newOrders.getOaddress());
		map.put("totalPrice", newOrders.getOtotalprice());
		map.put("status", newOrders.getOstate());
		if(newOrders.getOphone()!=null){map.put("status", newOrders.getOphone());}
		if(newOrders.getOpostcode()!=null){map.put("status", newOrders.getOpostcode());}
		if(newOrders.getOname()!=null){map.put("status", newOrders.getOname());}
		return map;
	}
}
