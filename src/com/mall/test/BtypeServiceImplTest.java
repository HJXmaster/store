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

import com.mall.entity.Btype;
import com.mall.service.BtypeService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class BtypeServiceImplTest {
	@Autowired
	BtypeService btypeService;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		System.out.println(btypeService.select(1));
		Btype nb=new Btype();
		nb.setBtname("美容洗护");
		System.out.println(btypeService.insert(nb));
		List<Btype> btypes=btypeService.selectAll();
		for(Btype btype : btypes){
			System.out.println(btype);
		}
	}

}
