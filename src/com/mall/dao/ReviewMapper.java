package com.mall.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.entity.Review;
@Repository
public interface ReviewMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(Review record);

    int insertSelective(Review record);

    Review selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Review record);

    int updateByPrimaryKey(Review record);
    
    List<Review> getReviewsByCid(Integer cid);
    
    int isPublished(Map<String, Object> map);
}