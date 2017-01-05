package com.spring.hello;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * 配置类
 * Created by ustc-lezg on 04/01/2017.
 */

@Configuration
@ComponentScan(basePackages = "com.spring.hello")
@PropertySource(value = {"classpath:jdbc.properties"})
@MapperScan(value = "com.spring.hello.mapper")
@EnableAspectJAutoProxy
@EnableWebMvc
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("driverClassName").trim());
        dataSource.setUrl(env.getProperty("url").trim());
        dataSource.setName(env.getProperty("username").trim());
        dataSource.setPassword(env.getProperty("password").trim());
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean ssfbean = new SqlSessionFactoryBean();
        ssfbean.setDataSource(dataSource());
        ssfbean.setTypeAliasesPackage("com.spring.hello.model");
        return ssfbean;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        try {
            return sqlSessionFactoryBean().getObject();
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
