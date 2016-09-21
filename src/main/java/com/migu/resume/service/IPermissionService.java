package com.migu.resume.service;

import java.util.List;

import com.migu.resume.persistence.permission.module.Permission;
import com.migu.resume.persistence.permission.module.PermissionExample;

public interface IPermissionService {
	Permission selectByPrimaryKey(Long id);
	List<Permission> selectByExample(PermissionExample example);
}
