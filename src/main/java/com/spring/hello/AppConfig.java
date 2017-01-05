package com.spring.hello;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



/**
 * 配置类
 * Created by ustc-lezg on 04/01/2017.
 */

@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.spring.hello")
public class AppConfig extends WebMvcConfigurerAdapter{

    /**
     * 默认
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
