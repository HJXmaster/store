package com.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.GoodsHobbyMapper;
import com.mall.dao.StypeMapper;
import com.mall.dao.UserHobbyMapper;
import com.mall.entity.GoodsHobby;
import com.mall.entity.UserHobby;
import com.mall.service.GoodsHobbyService;
import com.mall.service.GoodsPicsService;
import com.mall.service.UserHobbyService;
@Service
public class GoodsHobbyServiceImpl implements GoodsHobbyService{

	@Autowired
	private GoodsHobbyMapper goodsHobbyMapper;
	@Autowired
	private GoodsPicsService goodsPicsService;
	@Override
	public List<GoodsHobby> selectByCid(int cid) {
		// TODO Auto-generated method stub
		List<GoodsHobby> lists=goodsHobbyMapper.selectByCid(cid);
		for(GoodsHobby uh:lists){
			uh.getCommodity().setMiniPic(goodsPicsService.selectMiniPic(uh.getIntroCid()));
		}
		return lists;
	}
	@Override
	public List<GoodsHobby> selectSaleTopFive() {
		// TODO Auto-generated method stub
		return goodsHobbyMapper.selectSaleTopFive();
	}

}
