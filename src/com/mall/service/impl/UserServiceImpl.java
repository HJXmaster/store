package com.mall.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.UserMapper;
import com.mall.entity.User;
import com.mall.service.UserService;
import com.mall.tool.Encryption;
import com.mall.util.UObjects;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	/**
	 * 检查用户名是否存在
	 * 
	 * @param userName
	 *            用户名
	 * @return true /false 是否存在
	 */
	@Override
	public boolean checkUserName(String userName) {
		User user = userMapper.queryByUnickname(userName.trim());
		return user != null;
	}

	/**
	 * 根据昵称和密码获取用户
	 * 
	 * @param nickname
	 *            昵称
	 * @param password
	 *            密码
	 * @return User 用户
	 */
	@Override
	public User checkPassword(String nickname, String password) {

		User user = userMapper.queryByUnickname(nickname.trim());
		// 不存在用户
		if (user == null)
			return null;
		// 密码相同，使用MD5
		System.out.println("密码验证");
		if (user.getUpassword().equals(Encryption.getMD5Encryption(password)))
			return user;

		return null;
	}

	/**
	 * 根据UId获取用户
	 * 
	 * @param uid
	 *            用户id
	 * @return User 用户
	 */
	@Override
	public User getByUId(Integer uid) {
		if(uid==null)
			return null;
		User user = userMapper.selectByPrimaryKey(uid);
		return user;
	}

	/**
	 * 注册用户
	 * 
	 * @param user
	 *            新用户
	 * @return true/false 是否注册成功
	 */
	@Override
	public boolean register(User user) {

		if (!checkRegisterUser(user))
			return false;
		if(UObjects.isNonNullEmpty(user.getUpassword()))
		user.setUpassword(Encryption.getMD5Encryption(user.getUpassword()));
		int res = userMapper.insert(user);
		return res > 0;
	}
    //检查注册信息的合法性
	private boolean checkRegisterUser(User user) {
		User u = userMapper.queryByUnickname(user.getUnickname().trim());
		// 存在用户
		if (u != null)
			return false;

		boolean res = mustRequestUser(user);
		if (res) {
			UObjects.transferEmptyToNullInObject(user);
		}
		return res;
	}
   

	/**
	 * 查询所有用户
	 * 
	 * @return list 所有用户
	 */
	@Override
	public List<User> selectAll() {
		List<User> users = userMapper.selectAll();
		return users;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 *            用户
	 * @return true/false 是否更新成功
	 */
	@Override
	public boolean update(User user) {
		if (!checkUpateUser(user))
			return false;
		if(UObjects.isNonNullEmpty(user.getUpassword()))
			user.setUpassword(Encryption.getMD5Encryption(user.getUpassword()));
		int res = userMapper.updateByPrimaryKey(user);
		return res > 0;
	}
    //检查更新信息合法性
	private boolean checkUpateUser(User user) {
		UObjects.transferEmptyToNullInObject(user);
		User ou = userMapper.selectByPrimaryKey(user.getUid());
		//昵称没有修改
		if (Objects.equals(user.getUnickname(), ou.getUnickname()))
			return mustRequestUser(user);

		//查找昵称是否存在
		User u = userMapper.queryByUnickname(user.getUnickname().trim());
		// 存在用户
		if (u != null)
			return false;
		
		return mustRequestUser(user);
	}
    //user不为空的要求
	private boolean mustRequestUser(User user) {
		return UObjects.isNonNullEmpty(user.getUnickname())
				&& UObjects.isNonNullEmpty(user.getUpassword());
	}

	/**
	 * 删除用户
	 * 
	 * @param user
	 *            用户
	 * @return true/false 是否删除成功
	 */
	@Override
	public boolean delete(User user) {
		
		if(user==null||user.getUid()==null)
			return false;
		
		int res = userMapper.deleteByPrimaryKey(user.getUid());
		return res > 0;
	}

	/**
	 * 删除用户
	 * 
	 * @param uid
	 *            用户
	 * @return true/false 是否删除成功
	 */
	@Override
	public boolean deleteByUId(Integer uid) {
		int res = userMapper.deleteByPrimaryKey(uid);
		return res > 0;
	}

}
