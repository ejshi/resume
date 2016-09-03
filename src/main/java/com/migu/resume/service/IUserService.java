package com.migu.resume.service;

import java.util.List;

import com.migu.resume.persistence.module.User;
import com.migu.resume.persistence.module.UserExample;

public interface IUserService {
	 public User getUserById(int userId);
	 
	 public List<User> selectByExample(UserExample example);
}
