package com.migu.resume.persistence.demo.dao;

import com.migu.resume.persistence.demo.module.Demo;
import com.migu.resume.persistence.demo.module.DemoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DemoDao {
    int countByExample(DemoExample example);

    int deleteByExample(DemoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Demo record);

    int insertSelective(Demo record);

    List<Demo> selectByExample(DemoExample example);

    Demo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Demo record, @Param("example") DemoExample example);

    int updateByExample(@Param("record") Demo record, @Param("example") DemoExample example);

    int updateByPrimaryKeySelective(Demo record);

    int updateByPrimaryKey(Demo record);
}