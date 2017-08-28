package com.mall.controller.mk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.entity.Orders;
import com.mall.entity.Review;
import com.mall.entity.User;
import com.mall.service.OrdersService;
import com.mall.service.ReviewService;
import com.mall.util.UObjects;
/**
 * 评论管理
 * @author MK
 *
 */
@Controller
public class ReviewManagerController {

	@Autowired
	private ReviewService reviewService;
	@Autowired
	private OrdersService ordersService;
	
    /**
     * 查询商品的评论
     * @param id
     * @return
     */
    @ResponseBody
 	@RequestMapping("queryReview.do")
 	public Map<String,Object> queryReview(Integer cid){

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Review> reviews = reviewService.getReviewsByCid(cid);
		if (reviews == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("msg", "商品暂无评论");
			return map;
		}
		for (Review r : reviews) {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", r.getRid());
			Orders o = ordersService.select(r.getOid());
			if (o != null) {
				User u = o.getUser();
				if(u!=null)
				map.put("username", u.getUnickname());
			}
			map.put("time", UObjects.fomateDate(r.getRtime()));
			map.put("text", r.getRtext());
			map.put("grade", r.getGrade());
			list.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}

	/**
	 * 删除商品的评论
	 * 
	 * @param id
	 *            评论id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteReview.do")
	public Map<String, Object> deleteReview(Integer id) {

		boolean b = reviewService.delete(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", b);
		return map;
	}

}
