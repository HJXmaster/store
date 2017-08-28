package com.mall.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.entity.Stype;
import com.mall.entity.UserHobby;
@Repository
public interface UserHobbyMapper {

	List<UserHobby> selectByUser(int uid);

	List<UserHobby> selectTopFive();
}