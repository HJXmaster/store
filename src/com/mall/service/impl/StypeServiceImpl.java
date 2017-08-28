package com.mall.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mall.dao.BtypeMapper;
import com.mall.dao.StypeMapper;
import com.mall.entity.Stype;
import com.mall.entity.User;
import com.mall.service.StypeService;
import com.mall.util.UObjects;

@Service
public class StypeServiceImpl implements StypeService {
	
	@Autowired
	private StypeMapper stypeMapper;

	/**
	 * 删除小标签
	 * 
	 * @param stid
	 *            小标签id
	 * @return true false
	 */
	@Override
	public boolean delete(Integer stid) {
		if(stid==null)
			return false;
		
		int res = stypeMapper.deleteByPrimaryKey(stid);
		return res > 0;
	}

	/**
	 * 插入小标签
	 * 
	 * @param record
	 * @return true false
	 */
	@Override
	public boolean insert(Stype stype) {
		
        if(!checkInsertStype(stype))
        	return false;
        
		int res = stypeMapper.insert(stype);
		return res > 0;
	}

	// 检查新建小标签的信息的合法性
	private boolean checkInsertStype(Stype stype) {
		
		UObjects.transferEmptyToNullInObject(stype);
		//必须的要求
		if(!mustRequestStype(stype)){
			return false;
		}
		//小标签名字是否重复
		List<Stype> stypes=stypeMapper.selectByBtype(stype.getBtid());
		for(Stype s:stypes)
			if(Objects.equals(stype.getStname(), s.getStname()))
		        return false;
		
		return true;
	}
    //必须小标签的要求
	private boolean mustRequestStype(Stype stype) {
		
		return stype.getStname()!=null&&stype.getBtid()!=null;
	}

	/**
	 * 查询小标签
	 * 
	 * @param stid
	 *            小标签id
	 * @return stype 小标签信息
	 */
	@Override
	public Stype select(Integer stid) {
		if(stid==null)
			return null;
		
		Stype stype =stypeMapper.selectByPrimaryKey(stid);
		return stype;
	}

	/**
	 * 更新小标签
	 * 
	 * @param stype
	 *            小标签信息
	 * @return true false
	 */
	@Override
	public boolean update(Stype stype) {
		
		if(!checkUpdateStype(stype))
        	return false;
        
		int res = stypeMapper.updateByPrimaryKey(stype);
		return res > 0;
		
	}
    //检查更新的小标签
	private boolean checkUpdateStype(Stype stype) {
		
		UObjects.transferEmptyToNullInObject(stype);
		//必须的要求
		if(!mustRequestStype(stype)){
			return false;
		}
		Stype os =stypeMapper.selectByPrimaryKey(stype.getStid());
		
		//小标签名字是否重复
		List<Stype> stypes=stypeMapper.selectByBtype(stype.getBtid());
		for(Stype s:stypes)
			if(s.getStid()!=stype.getStid()
			&&Objects.equals(stype.getStname(), s.getStname()))
		        return false;
		
		return true;
	}

	/**
	 * 获得大标签所属小标签
	 * 
	 * @param btid
	 *            大标签id
	 * @return list
	 */
	@Override
	public List<Stype> selectByBtype(Integer btid) {
		if(btid==null)
			return null;
		List<Stype> stypes=stypeMapper.selectByBtype(btid);
		return stypes;
	}

	@Override
	public List<Stype> selectAll() {
	
		return stypeMapper.selectAll();
	}
}