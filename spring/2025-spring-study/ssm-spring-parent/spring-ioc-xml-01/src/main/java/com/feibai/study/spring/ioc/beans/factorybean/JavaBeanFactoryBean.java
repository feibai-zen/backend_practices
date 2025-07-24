package com.feibai.study.spring.ioc.beans.factorybean;

import org.springframework.beans.factory.FactoryBean;

public class JavaBeanFactoryBean implements FactoryBean<JavaBean> {

    @Override
    public JavaBean getObject() throws Exception {

        return new JavaBean();
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
