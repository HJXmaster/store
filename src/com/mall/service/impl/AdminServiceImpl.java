package com.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.AdminMapper;
import com.mall.entity.Admin;
import com.mall.service.AdminService;
import com.mall.util.UObjects;
@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	AdminMapper adminMapper;
	@Override
	public Admin getByAId(Integer aid) {
		return adminMapper.selectByPrimaryKey(aid);
	}

	@Override
	public Admin check(Integer aid, String password) {
		Admin admin=adminMapper.selectByPrimaryKey(aid);
		if(admin!=null&&admin.getApassword().equals(password)){
			return admin;
		}else 
			return null;
	}

	@Override
	public boolean deleteByAId(Integer aid) {
		return (adminMapper.deleteByPrimaryKey(aid)==1)?true:false;
	}

	@Override
	public boolean insert(Admin admin) {
		boolean f=false;
		try {
			f=(adminMapper.insert(admin)==1)?true:false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return f;
	}

	@Override
	public boolean updatebySelective(Admin admin) {
		
		
		boolean f=false;
		try {
			UObjects.transferEmptyToNullInObject(admin);
			f=(adminMapper.updateByPrimaryKeySelective(admin)==1)?true:false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return f;
	}

	@Override
	public boolean update(Admin admin) {
		return (adminMapper.updateByPrimaryKey(admin)==1)?true:false;
	}

	@Override
	public List<Admin> selectAll() {
		return adminMapper.selectAll();
	}

}
