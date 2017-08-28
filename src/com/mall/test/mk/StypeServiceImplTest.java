package com.mall.test.mk;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mall.entity.Stype;
import com.mall.service.StypeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml",
		"file:WebRoot/WEB-INF/springmvc-servlet.xml" })
public class StypeServiceImplTest {
    @Autowired
	StypeService stypeService;
	@Test
	public void testDelete() {
		boolean b=stypeService.delete(5);
		System.out.println(b);
	}

	@Test
	public void testInsert() {
		Stype s=new Stype();
		s.setStname("   ");
		s.setBtid(1);
		boolean b=stypeService.insert(s);
		System.out.println(b);
	}

	@Test
	public void testSelect() {
		Stype s=stypeService.select(12);
		System.out.println(s);
	}

	@Test
	public void testUpdate() {
		Stype s=stypeService.select(1);
		s.setStname(s.getStname()+"1 ");
		boolean res =stypeService.update(s);
		System.out.println(res);
	}

	@Test
	public void testSelectByBtype() {
		List<Stype> s=stypeService.selectByBtype(1);
		System.out.println(s);
	}

}
