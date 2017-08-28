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

import com.mall.entity.Admin;
import com.mall.service.AdminService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AdminServiceTest {
	@Autowired
	AdminService adminService;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		System.out.println(adminService.getByAId(1));
		System.out.println(adminService.check(2, "201430340107"));
//		Admin admin=new Admin();
//		admin.setAname("贺世宇");
//		admin.setApassword("201430340104");
//		admin.setAtype("超级管理员");
//		System.out.println(adminService.insert(admin));
		Admin admin=adminService.getByAId(3);
		admin.setAtype("普通管理员");
		System.out.println(adminService.update(admin));
		List<Admin> admins=adminService.selectAll();
		for(Admin a:admins){
			System.out.println(a);
		}
	}

}
