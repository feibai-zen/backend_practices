package com.feibai.study.spring.ioc;

import com.feibai.study.spring.ioc.beans.ClientServiceImpl;
import com.feibai.study.spring.ioc.beans.HappyComponent;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Spring_01_IoC_Test {

    @Test
    public void getBeanByBeanId() {
        //ApplicationContext context = createIoC1();
        ApplicationContext context = createIoC2();
        // 返回值是Object 类型，需要使用对象方法时，需要进行强转, 不推荐
        Object o = context.getBean("happyComponent1");

        HappyComponent happyComponent = (HappyComponent) context.getBean("happyComponent1");
        happyComponent.doWork();
        System.out.println(happyComponent == o);
        System.out.println(o);
        System.out.println(happyComponent);
    }

    @Test
    public void getBeanByBeanIdAndBeanClass() {
        ApplicationContext context = createIoC1();
        HappyComponent happyComponent = context.getBean("happyComponent2", HappyComponent.class);
        happyComponent.doWork();
    }

    @Test
    public void getBeanByClass() {
        ApplicationContext context = createIoC1();

        // 这种获取方式只限制于，容器中只有一个该类的实例对象
        // 也可以通过接口的形式获取bean. 比如 ClientServiceImpl的接口是 A
        // 可以通过  A clientService = context.getBean(A.class); 获取到 bean, 底层使用的是 instanceof 来判断的。
        ClientServiceImpl clientService = context.getBean(ClientServiceImpl.class);
        System.out.println(clientService);
    }


    private ApplicationContext createIoC1() {

        return new ClassPathXmlApplicationContext("spring-01.xml");
    }

    private ApplicationContext createIoC2() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        context.setConfigLocation("spring-01.xml");
        context.refresh();

        return context;
    }

}
