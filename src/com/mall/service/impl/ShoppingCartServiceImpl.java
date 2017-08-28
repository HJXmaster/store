package com.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mall.dao.PreOrderMapper;
import com.mall.entity.PreOrder;
import com.mall.service.ShoppingCartService;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	@Autowired
	private PreOrderMapper preOrderMapper;
	/**
	 * 删除购物车中某一商品
	 * 
	 * @param pid
	 *            购物车中某一商品id
	 * @return true false
	 */
	@Override
	public boolean deleteOne(PreOrder preOrder) {
		// 根据uid cid 查询
		PreOrder resultPreOrder = preOrderMapper.queryByUC(preOrder);
		return preOrderMapper.deleteByPrimaryKey(resultPreOrder.getPid()) > 0;
	}
	/**
	 * 添加商品到购物车
	 * @param preOrder 商品信息
	 * @return	true false
	 */
	@Override
	public boolean insertOne(PreOrder preOrder) {
		// 检查商品是否已存在
		PreOrder resultPreOrder = preOrderMapper.queryByUC(preOrder);
		if (resultPreOrder == null) {
			// 不存在则插入
			return preOrderMapper.insert(preOrder) > 0;
		} else {
			// 存在则更新个数
			int newCsize = preOrder.getCsize() + resultPreOrder.getCsize();
			resultPreOrder.setCsize(newCsize);
			return updateOne(resultPreOrder);
		}
	}

    /**
     * 更新购物车中某一商品
     * @param record 购物车中商品信息
     * @return true false
     */
	@Override
	public boolean updateOne(PreOrder record) {
		// 检查商品是否已存在
		PreOrder resultPreOrder = preOrderMapper.queryByUC(record);
		record.setPid(resultPreOrder.getPid());
		return preOrderMapper.updateByPrimaryKey(record) > 0;
	}
	
	/**
     * 根据用户与商品查询购物车
     * @param record 购物车中商品信息
     * @return true false
     */
	@Override
	public PreOrder queryByUC(PreOrder record) {
		return preOrderMapper.queryByUC(record);
	}
	
    /**
     * 获得购物车商品列表
     * @param uid 用户id
     * @return list
     */
	@Override
	public List<PreOrder> selectCart(Integer uid) {
		return preOrderMapper.selectByUid(uid);
	}
    /**
     * 清空购物车
     * @param uid 用户id
     * @return true false
     */
	@Override
	public boolean clearCart(Integer uid) {
		return preOrderMapper.deleteByUid(uid)>=0;
	}
}