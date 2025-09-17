package com.feibai.study.spring.ioc.beans;

import java.beans.ConstructorProperties;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    private int age;
    private String name;

    @ConstructorProperties({"age", "name", "userDao"})
    public UserService(int age, String name, UserDao userDao) {
        this.userDao = userDao;
        this.age = age;
        this.name = name;
    }
}
