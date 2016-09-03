package com.migu.resume;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;  
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*; 
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;  

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)  
@WebAppConfiguration  
@ContextConfiguration(locations = { "classpath:spring-config.xml","classpath:spring-mybatis.xml" })  
//当然 你可以声明一个事务管理 每个单元测试都进行事务回滚 无论成功与否  
@TransactionConfiguration(defaultRollback = true)  
//记得要在XML文件中声明事务哦~~~我是采用注解的方式  
@Transactional 
/**
 * Controller层中的没有搭建成功
 * @TODO 后期加上用spring MockMvc测试框架
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月2日 下午2:03:23
 * @version: V1.0
 */
public class ExampleTests {  
  
    @Autowired  
    private WebApplicationContext wac;  
  
    private MockMvc mockMvc;  
  
    @Before  
    public void setup() {  
        // webAppContextSetup 注意上面的static import  
        // webAppContextSetup 构造的WEB容器可以添加fileter 但是不能添加listenCLASS  
        // WebApplicationContext context =  
        // ContextLoader.getCurrentWebApplicationContext();  
        // 如果控制器包含如上方法 则会报空指针  
    	this.mockMvc = webAppContextSetup(this.wac).build();  
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }  
  
    @Test  
        //有些单元测试你不希望回滚  
        @Rollback(false)  
    public void ownerId() throws Exception {  
        mockMvc.perform((get("/user/showUser?id=1&sex=5"))).andExpect(status().isOk())  
                .andDo(print());  
    }  
}  