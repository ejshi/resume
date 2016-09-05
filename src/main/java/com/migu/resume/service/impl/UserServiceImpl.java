package com.migu.resume.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.migu.resume.persistence.dao.UserDao;
import com.migu.resume.persistence.module.User;
import com.migu.resume.persistence.module.UserExample;
import com.migu.resume.service.IUserService;
import com.migu.resume.util.JedisUtil;
import com.migu.resume.util.SerializeUtil;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JedisUtil jedisUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	public User getUserById(int userId) {
		User user = null;
		byte[] key = null;
		try {
			key = (userId+"").getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			boolean isExist = jedisUtil.exists(0, key);
			if(!isExist){
				user = userDao.selectByPrimaryKey(userId);
				jedisUtil.saveValueByKey(0, key, SerializeUtil.serialize(user), 2*60);
			}else{
				byte[] value = jedisUtil.getValueByKey(0, key);
				user = SerializeUtil.deserialize(value, User.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		user = userDao.selectByPrimaryKey(userId);
		return user;
	}

	@Override
	public List<User> selectByExample(UserExample example) {
		return userDao.selectByExample(example);
	}
}
