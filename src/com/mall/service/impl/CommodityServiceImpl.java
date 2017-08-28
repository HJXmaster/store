package com.mall.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.mall.dao.CommodityMapper;
import com.mall.dao.GoodsPicsMapper;
import com.mall.entity.Commodity;
import com.mall.service.CommodityService;
import com.mall.service.GoodsPicsService;
import com.mall.util.UObjects;
@Service
public class CommodityServiceImpl implements CommodityService {
	@Autowired
	CommodityMapper commodityMapper;
	@Autowired
	GoodsPicsService goodsPicsService;
	@Override
	public boolean delete(Integer cid) {
		return (commodityMapper.deleteByPrimaryKey(cid)==1)?true:false;
	}

	@Override
	public boolean insert(Commodity commodity) {
		return (commodityMapper.insert(commodity)==1)?true:false;
	}

	@Override
	public boolean insertSelective(Commodity commodity) {
		return (commodityMapper.insertSelective(commodity)==1)?true:false;
	}

	@Override
	public Commodity select(Integer cid) {
		Commodity c=commodityMapper.selectByPrimaryKey(cid);
		if(c!=null){
			c.setMiniPic(goodsPicsService.selectMiniPic(c.getCid()));
		}
		return c;
	}

	@Override
	public boolean updateBySelective(Commodity commodity) {
		return (commodityMapper.updateByPrimaryKeySelective(commodity)==1)?true:false;
	}

	@Override
	public boolean update(Commodity commodity) {
		return (commodityMapper.updateByPrimaryKey(commodity)==1)?true:false;
	}

	@Override
	public List<Commodity> selectAll(Integer btid, Integer stid) {
		Map<String,Integer> type=new HashMap<String, Integer>();
		type.put("btid", btid);
		type.put("stid", stid);
		return commodityMapper.selectAll(type);
	}

	@Override
	public List<Commodity> fuzzySearch(String condition) {
		return commodityMapper.fuzzySearch(condition);
	}

	@Override
	public List<Commodity> search(Integer btid, Integer stid, String condition,String currentPage,String pageSize) {
		Map<String,Object> type=new HashMap<String, Object>();
		type.put("btid", btid);
		type.put("stid", stid);
		type.put("condition", condition);
		int head=0;
		if(UObjects.isNonNullEmpty(currentPage)){
			head=(Integer.parseInt(currentPage)-1);
			if(head<0)head=0;
		}
		int size=10;
		if(UObjects.isNonNullEmpty(pageSize)){
			size=Integer.parseInt(pageSize);
			if(size<=0)size=10;
		}
		type.put("head", head*size);
		type.put("size", size);
		List<Commodity> lists=commodityMapper.search(type);
		for(Commodity c:lists){
			c.setMiniPic(goodsPicsService.selectMiniPic(c.getCid()));
		}
		return lists;
	}

	@Override
	public int searchCount(Integer btid, Integer stid, String condition) {
		Map<String,Object> type=new HashMap<String, Object>();
		type.put("btid", btid);
		type.put("stid", stid);
		type.put("condition", condition);
		return commodityMapper.searchCount(type);
	}

}
