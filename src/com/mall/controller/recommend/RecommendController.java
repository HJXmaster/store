package com.mall.controller.recommend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.entity.GoodsHobby;
import com.mall.entity.UserHobby;
import com.mall.service.GoodsHobbyService;
import com.mall.service.UserHobbyService;

@Controller

public class RecommendController {
	@Autowired
	UserHobbyService userHobbyService;
	@Autowired
	GoodsHobbyService goodsHobbyService;
	
	@ResponseBody
	@RequestMapping("/getRecommendByUser.do")
	public List getRecommendByUser(HttpSession session){
		System.out.println("向用户推荐商品");
		Integer uid=(Integer) session.getAttribute("uid");
		if(uid!=null&&!uid.equals(0)){
			List<UserHobby> lists=userHobbyService.selectByUser(uid);
			if(lists.size()==0){
				return userHobbyService.selectTopFive();
			}
			return lists;
		}else return userHobbyService.selectTopFive();
	}
	
	@ResponseBody
	@RequestMapping("/getRecommendByCid.do")
	public List getRecommendByUser(Integer cid){
		System.out.println("向用户推荐商品");
//		for(GoodsHobby uh:goodsHobbyService.selectByCid(cid)){
//			System.out.println(uh.getIntroCid()+uh.getRank());
//		}
		if(cid!=null&&!cid.equals(0)){
			return goodsHobbyService.selectByCid(cid);
		}else return null;
	}
	@ResponseBody
	@RequestMapping("/getRecommendBySale.do")
	public List getRecommendBySale(HttpSession session){
		System.out.println("推荐热销商品");
		List<GoodsHobby> lists=goodsHobbyService.selectSaleTopFive();
		return lists;
		
	}
}
