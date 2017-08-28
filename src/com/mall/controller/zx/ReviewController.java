package com.mall.controller.zx;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.entity.PreOrder;
import com.mall.entity.Review;
import com.mall.service.OrdersService;
import com.mall.service.ReviewService;

@Controller
public class ReviewController {
	@Autowired
	private ReviewService reviewService;

	@Autowired
	private OrdersService ordersService;

	// 9.发表评论
	@RequestMapping("publishReview.do")
	public @ResponseBody
	Map<String, Object> publishReview(HttpSession seesion, String text,
			int cid, int oid,Double grade) {

		Map<String, Object> map = new HashMap<String, Object>();

		// !验证用户是否为订单的用户
		Integer uid = (Integer) seesion.getAttribute("uid");
		if (!ordersService.select(oid).getUid().equals(uid)) {
			map.put("status", false);
			map.put("msg", "不可评论别人的订单！");
			return map;
		}
		System.out.println("是否已经评价");
		if(reviewService.isPublished(cid, oid)){
			map.put("status", false);
			map.put("msg", "你已经评价过商品");
			return map;
		}
		Review review = new Review();
		review.setRtext(text);
		review.setRtime(new Date());
		review.setOid(oid);
		review.setCid(cid);
		review.setGrade(grade);
		
		
		boolean value = reviewService.insert(review);
		map.put("status", value);
		if (!value) {
			map.put("msg", "请检查订单");
		}else {
			map.put("msg", "评价成功");
		}
		return map;
	}

}
