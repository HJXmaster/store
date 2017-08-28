package com.mall.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mall.entity.Commodity;
import com.mall.service.CommodityService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","file:WebRoot/WEB-INF/springmvc-servlet.xml"})
public class CommodityServiceImplTest {

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

		List<Commodity> commodities=commodityService.fuzzySearch("奶");
		for(Commodity cd:commodities){
			System.out.println(cd);
		}
		System.out.println(commodityService.delete(5));
	}

}
