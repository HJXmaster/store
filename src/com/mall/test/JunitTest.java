package com.mall.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JunitTest {
	@Autowired
	org.mybatis.spring.SqlSessionTemplate sqlSessionTemplate;
	@Test
	public void testUser(){
		System.out.println("控制器"+sqlSessionTemplate);
	}
}
