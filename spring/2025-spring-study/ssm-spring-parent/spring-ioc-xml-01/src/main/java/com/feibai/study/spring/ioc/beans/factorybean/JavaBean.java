package com.feibai.study.spring.ioc.beans.factorybean;

public class JavaBean {
    private int age;
    private String name;

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "JavaBean{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
