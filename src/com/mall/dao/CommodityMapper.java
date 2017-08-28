package com.mall.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.entity.Commodity;
@Repository
public interface CommodityMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKey(Commodity record);
    //
    List<Commodity> selectAll(Map<String,Integer> type);
    List<Commodity> fuzzySearch(String condition);
    List<Commodity> search(Map<String, Object> map);
    
    int searchCount(Map<String, Object> map);
}