package com.migu.resume.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.migu.resume.persistence.role.dao.RoleDao;
import com.migu.resume.persistence.role.module.Role;
import com.migu.resume.persistence.role.module.RoleExample;
import com.migu.resume.service.IRoleService;
/**
 * 角色查询service
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月21日 下午2:25:01
 * @version: V1.0
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService{
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Role selectByPrimaryKey(Long id) {
		return roleDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Role> selectByExample(RoleExample example) {
		return roleDao.selectByExample(example);
	}
}
