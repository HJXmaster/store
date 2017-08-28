package com.mall.dao;

import org.springframework.stereotype.Repository;

import com.mall.entity.Pics;
@Repository
public interface GoodsPicsMapper {

	String selectMiniPic(Integer cid);
	Pics selectPics(Integer cid);
}
