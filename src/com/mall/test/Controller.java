package com.mall.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.dao.UserMapper;
import com.mall.entity.User;

@org.springframework.stereotype.Controller
public class Controller {
	@Autowired
	UserMapper userMapper;
	@RequestMapping("testJson.do")
	public @ResponseBody User testJson(){
		System.out.println(userMapper.selectByPrimaryKey(1));
		User user=new User();
		user.setUname("曹祚潇");
		user.setUaddress("东软");
		return userMapper.selectByPrimaryKey(1);
	}
	@RequestMapping("userList.do")
	public @ResponseBody List<User> userList(){
		System.out.println(userMapper.selectByPrimaryKey(1));
		User user=new User();
		user.setUname("曹祚潇");
		user.setUaddress("东软");
		return userMapper.selectAll();
	}
}
