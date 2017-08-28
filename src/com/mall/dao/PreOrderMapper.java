package com.mall.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.entity.PreOrder;
@Repository
public interface PreOrderMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(PreOrder record);

    int insertSelective(PreOrder record);

    PreOrder selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(PreOrder record);

    int updateByPrimaryKey(PreOrder record);
    
    List<PreOrder> selectByUid(Integer uid);

	int deleteByUid(Integer uid);

	PreOrder queryByUC(PreOrder preOrder);
}