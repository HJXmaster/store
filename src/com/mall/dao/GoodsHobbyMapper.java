package com.mall.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.entity.GoodsHobby;
@Repository
public interface GoodsHobbyMapper {

	List<GoodsHobby> selectByCid(int cid);
	List<GoodsHobby> selectSaleTopFive();
}