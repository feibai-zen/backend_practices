package com.feibai.study.spring.ioc;

import com.feibai.study.spring.ioc.beans.lifecycle.JavaBeanLifeCycle2;
import com.feibai.study.spring.ioc.beans.lifecycle.JavaBeanLifeCycle3;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Spring_03_scope {

    @Test
    public void getBeanWhichScopeIsSingleton() {
        ClassPathXmlApplicationContext context = createContext();
        JavaBeanLifeCycle2 bean1 = context.getBean(JavaBeanLifeCycle2.class);
        JavaBeanLifeCycle2 bean2 = context.getBean(JavaBeanLifeCycle2.class);
        System.out.println(bean1);
        System.out.println(bean2);

        context.close(); // 容器关闭之前，会调用bean的detory-method方法
    }


    @Test
    public void getBeanWhichScopeIsPrototype() {
        ClassPathXmlApplicationContext context = createContext();
        JavaBeanLifeCycle3 bean1 = context.getBean(JavaBeanLifeCycle3.class);
        JavaBeanLifeCycle3 bean2 = context.getBean(JavaBeanLifeCycle3.class);
        System.out.println(bean1);
        System.out.println(bean2);

        context.close(); // 容器关闭之前，会调用bean的detory-method方法
    }

    private ClassPathXmlApplicationContext createContext() {

        return new ClassPathXmlApplicationContext("spring-01.xml");
    }
}
