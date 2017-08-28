package com.mall.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.entity.Btype;
@Repository
public interface BtypeService {
	/**
	 * 删除大标签
	 * @param btid 大标签id
	 * @return true false
	 */
    boolean delete(Integer btid);
    /**
     * 插入大标签
     * @param btype
     * @return true false
     */
    boolean insert(Btype btype);
    /**
     * 根据大标签动态插入
     * @param btype
     * @return true false
     */
    boolean insertSelective(Btype btype);
    /**
     * 根据id获得大标签
     * @param btid 大标签id
     * @return Btype 大标签
     */
    Btype select(Integer btid);
    /**
     * 根据大标签动态更新
     * @param btype
     * @return true false
     */
    boolean updateBySelective(Btype btype);
    /**
     * 更新大标签
     * @param btype
     * @return true false
     */
    boolean update(Btype btype);
    
    /**
     * 所有的大标签
     * @return list
     */
    List<Btype> selectAll();
}