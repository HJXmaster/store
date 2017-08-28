package com.mall.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.entity.Btype;
@Repository
public interface BtypeMapper {
    int deleteByPrimaryKey(Integer btid);

    int insert(Btype record);

    int insertSelective(Btype record);

    Btype selectByPrimaryKey(Integer btid);

    int updateByPrimaryKeySelective(Btype record);

    int updateByPrimaryKey(Btype record);
    
    //新增
    List<Btype> selectAll();
}