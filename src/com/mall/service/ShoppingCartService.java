package com.mall.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.entity.PreOrder;
@Repository
public interface ShoppingCartService {
	/**
	 * 删除购物车中某一商品
	 * @param preOrder 商品信息
	 * @return true false
	 */
	boolean deleteOne(PreOrder preOrder);
	/**
	 * 添加商品到购物车
	 * @param preOrder 商品信息
	 * @return	true false
	 */
    boolean insertOne(PreOrder preOrder);


    /**
     * 更新购物车中某一商品
     * @param record 购物车中商品信息
     * @return true false
     */
    boolean updateOne(PreOrder record);
    /**
     * 获得购物车商品列表
     * @param uid 用户id
     * @return list
     */
    List<PreOrder> selectCart(Integer uid);
    /**
     * 清空购物车
     * @param uid 用户id
     * @return true false
     */
    boolean clearCart(Integer uid);
    /**
     * 根据用户与商品查询购物车
     * @param uid 用户id
     * @return true false
     */
	PreOrder queryByUC(PreOrder record);
}