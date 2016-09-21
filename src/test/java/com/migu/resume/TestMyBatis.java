package com.migu.resume;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.migu.resume.persistence.demo.module.Demo;
import com.migu.resume.persistence.permission.module.Permission;
import com.migu.resume.service.IDemoService;
import com.migu.resume.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring.xml"})  
public class TestMyBatis {
    private static Logger logger = Logger.getLogger(TestMyBatis.class);  
    @Resource  
    private IDemoService demoService = null;  
    @Resource
    private IUserService userService = null;
	@Test  
    public void test1() {  
        Demo demo = demoService.getDemoById(1);  
        // System.out.println(user.getUserName());  
        // logger.info("值："+user.getUserName());  
        logger.info(JSON.toJSONString(demo));  
    }  
	@Test
	public void test2(){
		List<Permission> list = userService.selectPermissionsByUserId(1l);
		Assert.assertNotNull(list);
		System.out.println("====================="+list.size());
	}
}  

