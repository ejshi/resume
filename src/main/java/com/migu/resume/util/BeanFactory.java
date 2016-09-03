package com.migu.resume.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 实现beanfactory,方便对bean的获取
 * 注：本系统中推荐三种方式创建和获取bean: 1)autowired  2)通过在xml中手动配置  3)通过当前类的getBean方法进行获取
 * @author zhangpanfu
 */
public class BeanFactory implements ApplicationContextAware {
    private static ApplicationContext context;

    public BeanFactory() {
    }

    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        context = ctx;
    }

    public static ApplicationContext getContext() {

        return context;
    }

    public static  Object getBean(String name) {
        return context != null?context.getBean(name):null;
    }

    public static <T> T getBean(Class<T> clz) {
        return context != null?context.getBean(clz):null;
    }
}
