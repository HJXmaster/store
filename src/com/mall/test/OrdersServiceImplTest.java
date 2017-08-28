package com.mall.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mall.entity.OrderInfo;
import com.mall.entity.Orders;
import com.mall.service.CommodityService;
import com.mall.service.OrdersService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","file:WebRoot/WEB-INF/springmvc-servlet.xml"})
public class OrdersServiceImplTest {

	@Autowired
	OrdersService ordersService;
	@Autowired
	CommodityService commodityService;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Orders order=ordersService.select(1);
		order.setOstate("货到付款");
		ordersService.update(order);
		order=ordersService.select(1);
//		System.out.println(order);
		Date startTime=new Date();
		Calendar c=Calendar.getInstance();
		c.setTime(startTime);
		c.add(Calendar.MONTH, -1);
		Date endTime=new Date();
		List<Orders> orders=ordersService.getOrdersByTime(c.getTime(), endTime);
		for(Orders o:orders){
			System.out.println(o);
		}
	}
	@Test
	public void selectAll() {
	  List<Orders> orders=ordersService.getOrders();
	  System.out.println(orders);
	}

}
