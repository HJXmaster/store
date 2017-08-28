package com.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.StypeMapper;
import com.mall.dao.UserHobbyMapper;
import com.mall.entity.UserHobby;
import com.mall.service.GoodsPicsService;
import com.mall.service.UserHobbyService;
@Service
public class UserHobbyServiceImpl implements UserHobbyService{

	@Autowired
	private UserHobbyMapper userHobbyMapper;
	@Autowired
	private GoodsPicsService goodsPicsService;
	@Override
	public List<UserHobby> selectByUser(int uid) {
		// TODO Auto-generated method stub
		List<UserHobby> lists=userHobbyMapper.selectByUser(uid);
		for(UserHobby uh:lists){
			uh.getCommodity().setMiniPic(goodsPicsService.selectMiniPic(uh.getCid()));
		}
		return lists;
	}
	@Override
	public List<UserHobby> selectTopFive() {
		List<UserHobby> lists=userHobbyMapper.selectTopFive();
		for(UserHobby uh:lists){
			uh.getCommodity().setMiniPic(goodsPicsService.selectMiniPic(uh.getCid()));
		}
		return lists;
	}

}
