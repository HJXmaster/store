package com.mall.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.entity.Review;
@Repository
public interface ReviewService {
	/**
	 * 删除评论
	 * @param rid 评论id
	 * @return true false
	 */
    boolean delete(Integer rid);
    /**
     * 插入评论
     * @param record
     * @return true false
     */
    boolean insert(Review record);

    /**
     * 查找某条评论
     * @param rid 评论id
     * @return review 评论信息
     */
    Review selectOne(Integer rid);
    /**
     * 查询某个商品的评论
     * @param cid 商品id
     * @return list
     */
    List<Review> getReviewsByCid(Integer cid);
    
    boolean isPublished(Integer cid,Integer oid);
}