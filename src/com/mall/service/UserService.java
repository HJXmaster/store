package com.mall.service;

import java.util.List;

import com.mall.entity.User;

public interface UserService {
	/**
	 * 检查用户名是否存在
	 * @param userName  用户名
	 * @return true /false 是否存在
	 */
	boolean checkUserName(String userName);
	
	/**
	 * 根据昵称和密码获取用户
	 * @param nickname  昵称
	 * @param password  密码
	 * @return  User 用户
	 */
	User checkPassword(String nickname,String password);
	

	/**
	 * 根据UId获取用户
	 * @param uid  用户id
	 * @return  User 用户
	 */
	User getByUId(Integer uid);
	
	/**
	 * 注册用户
	 * @param user 新用户
	 * @return true/false 是否注册成功
	 */
	boolean register(User user);
	
	/**
	 * 查询所有用户
	 * @return list 所有用户
	 */
	List<User> selectAll();
	/**
	 * 更新用户信息
	 * @param user 用户
	 * @return  true/false 是否更新成功
	 */
	boolean update(User user);
	
	/**
	 * 删除用户
	 * @param user 用户
	 * @return true/false 是否删除成功
	 */
	boolean delete(User user);
	
	/**
	 * 删除用户
	 * @param uid 用户
	 * @return true/false 是否删除成功
	 */
	boolean deleteByUId(Integer uid);

	
}
