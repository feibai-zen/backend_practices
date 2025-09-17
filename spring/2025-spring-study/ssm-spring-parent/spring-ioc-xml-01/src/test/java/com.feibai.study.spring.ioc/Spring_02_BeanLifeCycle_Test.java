package com.feibai.study.spring.ioc;

import com.feibai.study.spring.ioc.beans.lifecycle.JavaBeanLifeCycle;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Spring_02_BeanLifeCycle_Test {


    @Test
    public void getBeanTest() {
        ClassPathXmlApplicationContext context = createContext();
        JavaBeanLifeCycle bean = context.getBean("javaBeanLifeCycle", JavaBeanLifeCycle.class);
        System.out.println(bean);

        context.close(); // 容器关闭之前，会调用bean的detory-method方法
    }

    private ClassPathXmlApplicationContext createContext() {

        return new ClassPathXmlApplicationContext("spring-01.xml");
    }
}
