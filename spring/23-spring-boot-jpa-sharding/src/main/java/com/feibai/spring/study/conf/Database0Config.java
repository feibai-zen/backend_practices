package com.feibai.spring.study.conf;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Data
@ConfigurationProperties(prefix = "database0")
@Component
public class Database0Config {
  private String url;
  private String username;
  private String password;
  private String driverClassName;
  private String databaseName;

  public DataSource createDataSource() {
    DruidDataSource result = new DruidDataSource();
    result.setDriverClassName(getDriverClassName());
    result.setUrl(getUrl());
    result.setUsername(getUsername());
    result.setPassword(getPassword());
    return result;
  }
}
