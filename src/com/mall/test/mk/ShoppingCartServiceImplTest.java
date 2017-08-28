package com.mall.test.mk;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mall.entity.PreOrder;
import com.mall.service.ShoppingCartService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml",
		"file:WebRoot/WEB-INF/springmvc-servlet.xml" })
public class ShoppingCartServiceImplTest {

	@Autowired
	ShoppingCartService service;
//	@Test
//	public void testDeleteOne() {
//		boolean res=service.deleteOne(new PreOrder().setCid(3));
//		System.out.println(res);
//	}

	@Test
	public void testInsertOne() {
		 PreOrder preOrder=new PreOrder();
		 preOrder.setUid(1);
		boolean res= service.insertOne(preOrder);
		 System.out.println(res);
	}

	@Test
	public void testUpdateOne() {
		List<PreOrder> preOrders=service.selectCart(1);
		 PreOrder preOrder=preOrders.get(preOrders.size()-1);
		 preOrder.setCsize(5);
		 boolean res=service.updateOne(preOrder);
		 System.out.println(res);
	}

	@Test
	public void testSelectCart() {
		 List<PreOrder> preOrders=service.selectCart(1);
		 System.out.println(preOrders);
	}

	@Test
	public void testClearCart() {
		boolean res=service.clearCart(1);
		System.out.println(res);
	}

}
