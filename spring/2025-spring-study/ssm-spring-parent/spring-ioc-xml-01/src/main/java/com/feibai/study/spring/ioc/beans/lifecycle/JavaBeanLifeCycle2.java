package com.feibai.study.spring.ioc.beans.lifecycle;


public class JavaBeanLifeCycle2 {

    /**
     * 必须是public 必须是void 必须是无参数  必须是无返回值
     */
    public void init_method_any() {
        System.out.println("the init method invoked......");

    }

    public void destory_method_any() {
        System.out.println("the clear method invoked......");
    }
}
