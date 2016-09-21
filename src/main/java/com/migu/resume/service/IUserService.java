package com.migu.resume.service;

import java.util.List;

import com.migu.resume.persistence.permission.module.Permission;
import com.migu.resume.persistence.user.module.User;
import com.migu.resume.persistence.user.module.UserExample;

public interface IUserService {
	User selectByPrimaryKey(Long id);
	List<User> selectByExample(UserExample example);
	/**
	 * 根据用户id获取用户权限信息
	 */
	public List<Permission> selectPermissionsByUserId(long id);
	/**
	 * 根据用户账号查询用户信息
	 * @param account
	 * @return
	 */
	public User selectByUserAccount(String account);
}
