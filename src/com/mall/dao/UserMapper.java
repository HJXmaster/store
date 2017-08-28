package com.mall.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.entity.User;
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    //新增
    List<User> selectAll();
    
    User queryByUnickname(String nickname);
    
}