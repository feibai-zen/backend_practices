package com.feibai.study.spring.ioc.beans.factorybean;

import org.springframework.beans.factory.FactoryBean;

import java.beans.ConstructorProperties;

public class JavaBeanFactoryBean implements FactoryBean<JavaBean> {

    private int age;
    private String name;

    @ConstructorProperties({"age", "name"})
    public JavaBeanFactoryBean(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public JavaBean getObject() throws Exception {
        JavaBean javaBean = new JavaBean();
        javaBean.setAge(this.age);
        javaBean.setName(this.name);

        return javaBean;
    }

    @Override
    public Class<?> getObjectType() {
        return JavaBean.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }

}
