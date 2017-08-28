package com.mall.test.mk;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mall.entity.User;
import com.mall.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","file:WebRoot/WEB-INF/springmvc-servlet.xml"})
public class UserServiceTest {

	@Autowired
	UserService userService;
	@Test
	public void testCheckUserName() {
		boolean b=userService.checkUserName("老曹1");
		System.out.println(b);
	}

	@Test
	public void testCheckPassword() {
		User b=userService.checkPassword("老曹","201430340101x");
		System.out.println(b);
	}

	@Test
	public void testGetByUId() {
		User b=userService.getByUId(11);
		System.out.println(b);
	}

	@Test
	public void testRegister() {
		User user=new User();
		user.setUnickname("老曹1");
		user.setUpassword("123");
		user.setUaddress("   ");
		boolean b=userService.register(user);
		System.out.println(b);
	}

	@Test
	public void testSelectAll() {
		List<User> users=userService.selectAll();
		System.out.println(users);
	}

	@Test
	public void testUpdate() {
		User b=userService.getByUId(1);
		b.setUaddress("华农"+b.getUaddress());
		boolean res=userService.update(b);
		System.out.println(res);
	}

	@Test
	public void testDelete() {
		User user=new User();
		user.setUid(6);
		user.setUnickname("老曹1");
		user.setUpassword("123");
		user.setUaddress("   ");
		boolean res=userService.delete(user);
		System.out.println(res);
	}

	@Test
	public void testDeleteByUId() {
		
		boolean res=userService.deleteByUId(7);
		System.out.println(res);
	}

}
