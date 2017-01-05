package com.spring.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 配置类
 * Created by ustc-lezg on 04/01/2017.
 */

@Configuration
@ComponentScan(basePackages = "com.spring.hello")
@EnableAspectJAutoProxy
@EnableWebMvc
public class AppConfig {

    @Autowired
    private Environment env;
}
