package com.mall.controller.wx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.entity.Admin;
import com.mall.service.AdminService;

@RequestMapping(value = "/mgr")
@Controller
public class AdminController {

	@Autowired
	AdminService adminService;

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	// ok
	@RequestMapping(value = "/loginAdmin.do", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> adminLogin(HttpSession session,@ModelAttribute Admin admin) {
		Map<String, Object> map = new HashMap<String, Object>();
		Admin checkAdmin = adminService.check(admin.getAid(), admin.getApassword());
		// 登录成功
		if (checkAdmin != null) {
			session.setAttribute("aid", checkAdmin.getAid());
			map.put("status", true);
			map.put("msg", "管理员登录成功");
			map.put("aid", checkAdmin.getAid());
		} else {
			map.put("status", false);
			map.put("msg", "管理员登录失败");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("checkLogin.do")
	public Map<String, Object> checkLogin(HttpSession seesion) {

		Integer adminId=(Integer) seesion.getAttribute("aid");
		Admin user =adminService.getByAId(adminId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", user != null);
		if(user!=null)
		 map.put("username", user.getAname());
		return map;
	}
	// ok
	@RequestMapping(value = "/queryAdminInfo.do", method = RequestMethod.GET)
	public @ResponseBody
	List<Admin> queryAdminInfo() {
		List<Admin> list = adminService.selectAll();
		return list;
	}

	@RequestMapping(value = "/queryAdmin.do", method = RequestMethod.GET)
	public @ResponseBody
	Admin queryAdmin(HttpSession seesion) {
		Integer adminId = (Integer) seesion.getAttribute("aid");
		Admin user = adminService.getByAId(adminId);
		return user;
	}

	// ok
	@RequestMapping(value = "/addAdmin.do", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> addAdmin(@ModelAttribute Admin admin) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isAdd = adminService.insert(admin);
		// 注册成功
		if (isAdd == true) {
			map.put("status", true);
			map.put("msg", "管理员注册成功");
		} else {
			map.put("status", false);
			map.put("msg", "管理员注册失败");
		}
		return map;
	}

	// ok
	@RequestMapping(value = "/updateAdmin.do", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateAdmin(@ModelAttribute Admin admin) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isUpdate = adminService.updatebySelective(admin);
		// 修改成功
		if (isUpdate == true) {
			map.put("status", true);
			map.put("msg", "修改成功");
		} else {
			map.put("status", false);
			map.put("msg", "修改失败");
		}
		return map;
	}

	// ok，只可以删除普通管理员
	@RequestMapping(value = "/deleteAdmin.do", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> deleteAdmin(@RequestParam("aid") Integer aid) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isDelete = adminService.deleteByAId(aid);
		// 修改成功
		if (isDelete == true) {
			map.put("status", true);
			map.put("msg", "删除成功");
		} else {
			map.put("status", false);
			map.put("msg", "删除失败");
		}
		return map;
	}
	@RequestMapping(value = "/logoutAdmin.do", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> logoutUser(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		session.setAttribute("aid", null);
		if((Integer)session.getAttribute("aid")==null){
		
			map.put("status", true);
			map.put("msg", "注销成功");
		}else {
			map.put("status", false);
			map.put("msg", "注销失败");
		}

		return map;
	}
}
