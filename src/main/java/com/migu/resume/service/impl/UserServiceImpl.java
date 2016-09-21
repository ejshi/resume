package com.migu.resume.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.migu.resume.persistence.permission.module.Permission;
import com.migu.resume.persistence.user.dao.UserDao;
import com.migu.resume.persistence.user.dao.UserPermissionDao;
import com.migu.resume.persistence.user.module.User;
import com.migu.resume.persistence.user.module.UserExample;
import com.migu.resume.service.IUserService;
import com.migu.resume.util.CollectionUtils;
/**
 * 用户查询service
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月21日 下午2:25:01
 * @version: V1.0
 */
@Service("userService")
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserPermissionDao userPermissionDao;
	@Override
	public User selectByPrimaryKey(Long id) {
		return userDao.selectByPrimaryKey(id);
	}

	@Override
	public List<User> selectByExample(UserExample example) {
		return userDao.selectByExample(example);
	}

	@Override
	public List<Permission> selectPermissionsByUserId(long id) {
		return userPermissionDao.selectPermissionsByUserId(id);
	}

	@Override
	public User selectByUserAccount(String account) {
		UserExample example = new UserExample();
		example.createCriteria().andAccountEqualTo(account);
		List<User> userList = userDao.selectByExample(example);
		if(CollectionUtils.isEmpty(userList)){
			return null;
		}
		return userList.get(0);
	}
}
