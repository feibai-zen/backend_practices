package com.feibai.study.spring.ioc;

import com.feibai.study.spring.ioc.beans.factorybean.JavaBean;
import com.feibai.study.spring.ioc.beans.lifecycle.JavaBeanLifeCycle2;
import com.feibai.study.spring.ioc.beans.lifecycle.JavaBeanLifeCycle3;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Spring_03_create_with_factoryBean {

    @Test
    public void getBeanWhichScopeIsSingleton() {
        ClassPathXmlApplicationContext context = createContext();
        JavaBean bean = context.getBean("javaBean", JavaBean.class);
        System.out.println(bean);

    }

    private ClassPathXmlApplicationContext createContext() {

        return new ClassPathXmlApplicationContext("spring-01.xml");
    }
}
