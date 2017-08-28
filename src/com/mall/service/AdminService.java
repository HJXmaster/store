package com.mall.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.entity.Admin;
import com.mall.entity.User;
@Repository
public interface AdminService {
	
	/**
	 * 获取管理员
	 * @param aid  管理员id
	 * @return  Admin 管理员
	 */
	Admin getByAId(Integer aid);
	/**
	 * 登录验证id和密码
	 * @param aid 管理员id
	 * @param password 管理员密码
	 * @return Admin 管理员
	 */
	Admin check(Integer aid,String password);
	
	/**
	 * 删除管理员
	 * @param aid  管理员id
	 * @return true/false 是否删除
	 */
    boolean deleteByAId(Integer aid);

    /**
     * 保存管理员
     * @param admin  管理员
     * @return true/false 是否保存
     */
    boolean  insert(Admin admin);

    /**
     * 根据管理员数据动态更新
     * @param admin 管理员
     * @return true/false 是否动态更新
     */
    boolean  updatebySelective(Admin admin);

    /**
     * 更新管理员所有数据
     * @param admin 管理员
     * @return true/false 是否更新
     */
    boolean  update(Admin admin);
    
    /**
     * 查找所有管理员
     * @return List
     */
    List<Admin> selectAll();
}