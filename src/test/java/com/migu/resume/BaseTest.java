package com.migu.resume;


import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 作为单元测试的基类
 * @author zhangpanfu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mvc.xml","classpath:spring-mybatis.xml" })
@WebAppConfiguration
public abstract class BaseTest {
    protected MockMvc mockMvc;
    public void setWac(WebApplicationContext wac) {
        this.wac = wac;
    }
    @Autowired
    protected  WebApplicationContext wac;



    @Before
    public void autoSetBean() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }




}


