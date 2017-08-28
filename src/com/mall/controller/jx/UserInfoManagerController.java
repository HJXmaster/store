package com.mall.controller.jx;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.entity.User;
import com.mall.service.UserService;
import com.mall.util.UObjects;

@Controller
public class UserInfoManagerController {
	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping("queryInfo.do")
	public Object queryInfo(HttpSession seesion){
		Integer uid=(Integer)seesion.getAttribute("uid");
		Map<String,String> map=new HashMap<String, String>();
		if(uid==null){
			map.put("msg", "请先登录");
			return map;
		}
		return userService.getByUId(uid);
	}
	@ResponseBody
	@RequestMapping("modify.do")
	public Map modify(HttpSession seesion,String unickname,String uemail,String uaddress,String uphone,String uidcard,String uname,String upostcode){
		Integer uid=(Integer)seesion.getAttribute("uid");
		User user=userService.getByUId(uid);
		if(UObjects.isNonNullEmpty(unickname)){
			user.setUnickname(unickname.trim());
		}
		if(UObjects.isNonNullEmpty(uemail)){
			user.setUemail(uemail.trim());
		}
		if(UObjects.isNonNullEmpty(uaddress)){
			user.setUaddress(uaddress.trim());
		}
		if(UObjects.isNonNullEmpty(uidcard)){
			user.setUidcard(uidcard);
		}
		if(UObjects.isNonNullEmpty(uphone)){
			user.setUphone(uphone);
		}
		if(UObjects.isNonNullEmpty(uname)){
			user.setUname(uname);
		}
		if(UObjects.isNonNullEmpty(upostcode)){
			user.setUpostcode(upostcode);
		}
		Map map=new HashMap<String, Object>();
		if(userService.update(user)){
			map.put("status", true);
			map.put("msg", "修改个人资料成功");
		}else {
			map.put("status", false);
			map.put("msg", "修改个人资料失败");
		}
		return map;
	}
}
