package com.mall.controller.wr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.entity.User;
import com.mall.service.UserService;
@RequestMapping("/mgr")
@Controller
public class UserManagerController {
	@Autowired
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//查询会员信息
	@ResponseBody
	@RequestMapping(value ="/queryInfo.do")
	public Map<String, Object> getUserByUid(Integer uid){
		User user=userService.getByUId(uid);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("uid",user.getUid());
		map.put("unickname", user.getUnickname());
		map.put("uemail", user.getUemail());
		map.put("uaddress", user.getUaddress());
		map.put("uphone", user.getUphone());
		map.put("uidcard", user.getUidcard());
		map.put("uname", user.getUname());
		map.put("upostcode", user.getUpostcode());
		return map;
	}
	
	//查询所有会员信息
	@ResponseBody
	@RequestMapping(value ="/queryAllUser.do")
	public List<User> getAllUser(){
		return userService.selectAll();
	}
	
	//删除会员
	@ResponseBody
	@RequestMapping(value ="/deleteUser.do")
	public Map<String, Object> deleteUser(Integer uid){
		boolean ifdelete= userService.deleteByUId(uid);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("status", ifdelete);
		if(!ifdelete)
			map.put("msg", "会员"+(uid==null?"":uid)+"不存在");
		return map;
	}

}
