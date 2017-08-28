package com.mall.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.entity.Admin;
@Repository
public interface AdminMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    List<Admin> selectAll();
}