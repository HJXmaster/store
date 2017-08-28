package com.mall.service;

import java.util.List;

import com.mall.entity.UserHobby;

public interface UserHobbyService {

	List<UserHobby> selectByUser(int uid);
	List<UserHobby> selectTopFive();
}
