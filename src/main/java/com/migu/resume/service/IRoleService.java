package com.migu.resume.service;

import java.util.List;

import com.migu.resume.persistence.role.module.Role;
import com.migu.resume.persistence.role.module.RoleExample;

public interface IRoleService {
	Role selectByPrimaryKey(Long id);
	List<Role> selectByExample(RoleExample example);
}
