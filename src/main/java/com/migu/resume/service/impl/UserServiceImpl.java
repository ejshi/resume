package com.migu.resume.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.migu.resume.persistence.dao.UserDao;
import com.migu.resume.persistence.module.User;
import com.migu.resume.persistence.module.UserExample;
import com.migu.resume.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User getUserById(int userId) {
		User user = userDao.selectByPrimaryKey(userId);
		return user;
	}

	@Override
	public List<User> selectByExample(UserExample example) {
		return userDao.selectByExample(example);
	}
}
