package com.mall.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.entity.Commodity;
@Repository
public interface CommodityService {
	/**
	 * 根据商品id删除商品
	 * @param cid 商品id
	 * @return true false
	 */
    boolean delete(Integer cid);
    /**
     * 添加商品
     * @param commodity 商品信息
     * @return true false
     */
    boolean insert(Commodity commodity);
    /**
     * 动态添加商品
     * @param commodity 商品信息
     * @return true false
     */
    boolean insertSelective(Commodity commodity);
    /**
     * 查询商品
     * @param cid 商品id
     * @return Commodity 商品信息
     */
    Commodity select(Integer cid);
    /**
     * 动态更新商品信息
     * @param commodity 商品信息
     * @return true false
     */
    boolean updateBySelective(Commodity commodity);
    /**
     * 更新商品信息
     * @param commodity 商品信息
     * @return true false
     */
    boolean update(Commodity commodity);
    /**
     * 查询所有商品
     * @param btid 大标签id
     * @param stid 小标签id
     * @return list
     */
    List<Commodity> selectAll(Integer btid,Integer stid);
    /**
     * 模糊搜索商品
     * @param condition 模糊语句
     * @return list
     */
    List<Commodity> fuzzySearch(String condition);
	List<Commodity> search(Integer btid,Integer stid,String condition,String currentPage,String pageSize);
	
	int searchCount(Integer btid,Integer stid,String condition);
}