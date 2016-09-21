package com.migu.resume.persistence.user.dao;

import java.util.List;

import com.migu.resume.persistence.permission.module.Permission;
/**
 * 用户权限查询dao
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月21日 下午3:15:40
 * @version: V1.0
 */
public interface UserPermissionDao {
	/**
	 * 根据用户id，查询用户权限
	 * @param id
	 * @return
	 */
	public List<Permission> selectPermissionsByUserId(Long id);
}
