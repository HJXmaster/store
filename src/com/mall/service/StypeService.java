package com.mall.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.entity.Stype;
@Repository
public interface StypeService {
	/**
	 * 删除小标签
	 * @param stid 小标签id
	 * @return true false
	 */
    boolean delete(Integer stid);
    /**
     * 插入小标签
     * @param stype
     * @return true false
     */
    boolean insert(Stype stype);

    /**
     * 查询小标签
     * @param stid 小标签id
     * @return stype 小标签信息
     */
    Stype select(Integer stid);

    /**
     * 更新小标签
     * @param stype 小标签信息
     * @return true false
     */
    boolean update(Stype stype);
    /**
     * 获得大标签所属小标签
     * @param btid 大标签id
     * @return list
     */
	List<Stype> selectByBtype(Integer btid);
	List<Stype> selectAll();
}