package com.feibai.study.spring.ioc;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcTemplateTest {

    public void test() {
        // 0. 创建连接池对象
        DruidDataSource druidDatasource = new DruidDataSource();

        druidDatasource.setUrl("");
        druidDatasource.setDriverClassName("");
        druidDatasource.setUsername("");
        druidDatasource.setPassword("");
        druidDatasource.setMaxActive(5);


        // 1. 实例化对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(druidDatasource);

        // 2. 调用方法即可
        // jdbcTemplate.update() DDL DML DCL ..... 非查询语句
        // jdbcTemplate.queryForObject()  DQL 查询单个对象
        // jdbcTemplate.query()  DQL  查询集合

    }


}
