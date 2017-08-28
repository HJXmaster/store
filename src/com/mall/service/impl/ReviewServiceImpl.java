package com.mall.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mall.dao.ReviewMapper;
import com.mall.entity.Review;
import com.mall.service.ReviewService;
@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewMapper reviewMapper;
	/**
	 * 删除评论
	 * @param rid 评论id
	 * @return true false
	 */
	@Override
    public boolean delete(Integer rid) {
		return reviewMapper.deleteByPrimaryKey(rid)>0;
	}
    /**
     * 插入评论
     * @param record
     * @return true false
     */
	@Override
    public boolean insert(Review record) {
		return reviewMapper.insert(record)>0;
	}

    /**
     * 查找某条评论
     * @param rid 评论id
     * @return review 评论信息
     */
	@Override
    public Review selectOne(Integer rid) {
		return reviewMapper.selectByPrimaryKey(rid);
	}
    /**
     * 查询某个商品的评论
     * @param cid 商品id
     * @return list
     */
	@Override
    public List<Review> getReviewsByCid(Integer cid) {
		return reviewMapper.getReviewsByCid(cid);
	}
	@Override
	public boolean isPublished(Integer cid, Integer oid) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("cid", cid);
		map.put("oid", oid);
		if(reviewMapper.isPublished(map)>=1){
			return true;
		}
		return false;
	}
}