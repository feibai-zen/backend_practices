package com.feibai.study.spring.ioc;

import com.feibai.study.spring.ioc.beans.factorybean.JavaBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Spring_04_create_with_factoryBean {

    @Test
    public void getBeanWhichScopeIsSingleton() {
        ClassPathXmlApplicationContext context = createContext();
        JavaBean bean = context.getBean("javaBean", JavaBean.class);

        Object bean1 = context.getBean("&javaBean");
        System.out.println(bean);
        System.out.println(bean1);

    }

    private ClassPathXmlApplicationContext createContext() {

        return new ClassPathXmlApplicationContext("spring-01.xml");
    }
}
