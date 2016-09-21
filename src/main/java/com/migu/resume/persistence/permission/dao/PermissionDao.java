package com.migu.resume.persistence.permission.dao;

import com.migu.resume.persistence.permission.module.Permission;
import com.migu.resume.persistence.permission.module.PermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionDao {
    int countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}