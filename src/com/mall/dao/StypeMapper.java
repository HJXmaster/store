package com.mall.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.entity.Stype;
@Repository
public interface StypeMapper {
    int deleteByPrimaryKey(Integer stid);

    int insert(Stype record);

    int insertSelective(Stype record);

    Stype selectByPrimaryKey(Integer stid);

    int updateByPrimaryKeySelective(Stype record);

    int updateByPrimaryKey(Stype record);
    //======
    List<Stype> selectByBtype(int btid);

	List<Stype> selectAll();
    
    
}