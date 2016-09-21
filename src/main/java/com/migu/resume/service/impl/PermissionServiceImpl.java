package com.migu.resume.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.migu.resume.persistence.permission.dao.PermissionDao;
import com.migu.resume.persistence.permission.module.Permission;
import com.migu.resume.persistence.permission.module.PermissionExample;
import com.migu.resume.service.IPermissionService;
/**
 * 权限资源查询service
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月21日 下午2:25:01
 * @version: V1.0
 */
@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService{
	@Autowired
	private PermissionDao permissionDao;
	
	@Override
	public Permission selectByPrimaryKey(Long id) {
		return permissionDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Permission> selectByExample(PermissionExample example) {
		return permissionDao.selectByExample(example);
	}
}
