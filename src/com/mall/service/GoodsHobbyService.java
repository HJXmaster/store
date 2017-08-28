package com.mall.service;

import java.util.List;

import com.mall.entity.GoodsHobby;
import com.mall.entity.UserHobby;

public interface GoodsHobbyService {

	List<GoodsHobby> selectByCid(int cid);
	List<GoodsHobby> selectSaleTopFive();
}
