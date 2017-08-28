package com.mall.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mall.dao.CommodityMapper;
import com.mall.entity.Pics;
import com.mall.entity.PreOrder;
import com.mall.service.GoodsHobbyService;
import com.mall.service.GoodsPicsService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml",
"file:WebRoot/WEB-INF/springmvc-servlet.xml" })
public class CommodityMapperTest {
@Autowired
CommodityMapper commodityMapper;
@Autowired
GoodsPicsService goodsPicsService;

	@Test
	public void testPics() {
		System.out.println(goodsPicsService.selectMiniPic(16));
		Pics pics=goodsPicsService.selectPics(16);
		System.out.println(pics);
	}
	
	@Test
	public void test() {
		Map<String,Integer> paramts=new HashMap<String, Integer>();
		paramts.put("btid", 1);
		paramts.put("stid", 1);
		System.out.println(commodityMapper.selectAll(paramts));
	}

}
