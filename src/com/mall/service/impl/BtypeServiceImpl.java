package com.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.BtypeMapper;
import com.mall.dao.StypeMapper;
import com.mall.entity.Btype;
import com.mall.service.BtypeService;
import com.mall.util.UObjects;
@Service
public class BtypeServiceImpl implements BtypeService{
	@Autowired
	BtypeMapper btypeMapper;
	@Override
	public boolean delete(Integer btid) {
		return (btypeMapper.deleteByPrimaryKey(btid)==1)?true:false;
	}

	@Override
	public boolean insert(Btype btype) {
		boolean f=false;
		try {
			UObjects.transferEmptyToNullInObject(btype);
			f=(btypeMapper.insert(btype)==1)?true:false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return f;

	}

	@Override
	public boolean insertSelective(Btype btype) {
		boolean f=false;
		try {
			UObjects.transferEmptyToNullInObject(btype);
			f=(btypeMapper.insertSelective(btype)==1)?true:false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return f;

	}

	@Override
	public Btype select(Integer btid) {
		if(btid==null)
			return null;
		return btypeMapper.selectByPrimaryKey(btid);
	}

	@Override
	public boolean updateBySelective(Btype btype) {
		boolean f=false;
		try {
			UObjects.transferEmptyToNullInObject(btype);
			f=(btypeMapper.updateByPrimaryKeySelective(btype)==1)?true:false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return f;
	}

	@Override
	public boolean update(Btype btype) {
		boolean f=false;
		try {
			UObjects.transferEmptyToNullInObject(btype);
			f=(btypeMapper.updateByPrimaryKey(btype)==1)?true:false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return f;	
	}

	@Override
	public List<Btype> selectAll() {
		return btypeMapper.selectAll();
	}
	
}
