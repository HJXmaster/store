package com.mall.controller.wx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;












import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.entity.Commodity;
import com.mall.entity.PreOrder;
import com.mall.entity.ShoppingCart;
import com.mall.entity.User;
import com.mall.service.ShoppingCartService;
import com.mall.service.UserService;
import com.mall.util.ShoppingCartUtil;
import com.mall.util.UObjects;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private ShoppingCartService shoppingCartService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// ok
	// 检查用户名是否重复
	@RequestMapping(value = "/checkUserName.do", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> checkUserName(@RequestParam("unickname") String unickname) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isCheck = userService.checkUserName(unickname);

		if (isCheck == true) {
			// 重复
			map.put("status", true);
			map.put("msg", "昵称已被注册");
		} else {
			// 不存在
			map.put("status", false);
			map.put("msg", "该账号未被使用");
		}
		return map;
	}

	// ok
	// 用户注册
	@RequestMapping(value = "/signUp.do", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> register(@ModelAttribute User user,String uconfirmPassword) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(userService.checkUserName(user.getUnickname())){
			map.put("status", false);
			map.put("msg", "昵称已被注册");
			return map;
		}
		if(!UObjects.isNonNullEmpty(user.getUnickname())){
			map.put("status", false);
			map.put("msg", "昵称不能为空");
			return map;
		}
		if(!UObjects.isNonNullEmpty(user.getUquestion())){
			map.put("status", false);
			map.put("msg", "提示问题不能为空");
			return map;
		}
		if(!UObjects.isNonNullEmpty(user.getUanswer())){
			map.put("status", false);
			map.put("msg", "提示问题答案不能为空");
			return map;
		}
		if(!UObjects.isNonNullEmpty(user.getUpassword())){
			map.put("status", false);
			map.put("msg", "密码不能为空");
			return map;
		}
		if(!user.getUpassword().equals(uconfirmPassword)){
			map.put("status", false);
			map.put("msg", "两次密码不一致");
			return map;
		}
		boolean isRegister = userService.register(user);
		// true为注册成功
		if (isRegister == true) {
			map.put("status", true);
			map.put("msg", "注册成功");
		} else {
			map.put("status", false);
			map.put("msg", "注册失败");
		}
		return map;
	}

	// ok
	// 登录
	@RequestMapping(value = "/loginUser.do", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> loginUser(HttpServletRequest request,HttpServletResponse response,HttpSession session,@ModelAttribute User user) {
		System.out.println("用户名："+user.getUnickname());
		Map<String, Object> map = new HashMap<String, Object>();
		User checkUser = userService.checkPassword(user.getUnickname(),user.getUpassword());
		// 登录成功
		if (checkUser != null) {
			session.setAttribute("uid", checkUser.getUid());
			map.put("status", true);
			map.put("msg", "登录成功");
			map.put("uid", checkUser.getUid());
			ShoppingCart shoppingCart=ShoppingCartUtil.getShoppingCart(request);
			if(!shoppingCart.getItems().isEmpty()){
				shoppingCart.setUser(checkUser.getUid());
				System.out.println("登录成功，合并购物车"+shoppingCart.getItems().size());
				List<PreOrder> preOrders=shoppingCart.getItems();
				for(PreOrder preOrder:preOrders){
					System.out.println(preOrder);
					PreOrder po=shoppingCartService.queryByUC(preOrder);
					if (po == null) {
						shoppingCartService.insertOne(preOrder);
						continue;
					} else {
						if (po.getCsize()< preOrder.getCsize()) {
							po.setCsize(preOrder.getCsize());
						}
						shoppingCartService.updateOne(po);
					}
				}
				map.put("msg", "登录成功,更新购物车成功");
			}
			Cookie cookie =new Cookie("shoppingCart",null);
			cookie.setPath("/");
            cookie.setMaxAge(0);
			response.addCookie(cookie);
		} else {
			map.put("status", false);
			map.put("msg", "登录失败");
		}

		return map;
	}

//	// ok
//	@RequestMapping(value = "/queryInfo.do", method = RequestMethod.GET)
//	public @ResponseBody
//	List<User> queryAll() {
//		List<User> list = userService.selectAll();
//		return list;
//	}
//
//	// ok
//	// 前端需要传uid进来
//	@RequestMapping(value = "/modify.do", method = RequestMethod.POST)
//	public @ResponseBody
//	Map<String, Object> modify(@ModelAttribute User user) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		boolean isModify = userService.updateByPrimaryKeySelective(user);
//		if (isModify == true) {
//			map.put("status", true);
//			map.put("msg", "修改个人资料成功");
//		} else {
//			map.put("status", false);
//			map.put("msg", "修改个人资料失败");
//		}
//
//		return map;
//	}

	@RequestMapping(value = "/modifyPassword.do", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> modifyPassword(HttpSession session,String oldPassword,String newPassword,String confirmPassword) {
		System.out.println("密码"+oldPassword+" "+newPassword);
		Map<String, Object> map = new HashMap<String, Object>();
		Integer uid=(Integer)session.getAttribute("uid");
		User checkUser = userService.getByUId(uid);
		System.out.println("密码："+oldPassword+"  "+newPassword);
		String checkPassword = checkUser.getUpassword();
		if (!checkPassword.equals(oldPassword)) {
			map.put("status", false);
			map.put("msg", "旧密码有误");
			return map;
		}
		if(!UObjects.isNonNullEmpty(newPassword)){
			map.put("status", false);
			map.put("msg", "新密码不能为空");
			return map;
		}
		if(!newPassword.equals(confirmPassword)){
			map.put("status", false);
			map.put("msg", "两次输入的密码不一致");
			return map;
		}
		checkUser.setUpassword(newPassword);
		boolean isModify = userService.update(checkUser);
		if (isModify == true) {
			map.put("status", true);
			map.put("msg", "修改密码成功");
		} else {
			map.put("status", false);
			map.put("msg", "修改密码失败");
		}
		return map;
	}
	@RequestMapping(value = "/logoutUser.do", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> logoutUser(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		session.setAttribute("uid", null);
		if((Integer)session.getAttribute("uid")==null){
		
			map.put("status", true);
			map.put("msg", "注销成功");
		}else {
			map.put("status", false);
			map.put("msg", "注销失败");
		}

		return map;
	}
}
