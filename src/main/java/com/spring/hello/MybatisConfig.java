package com.spring.hello;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * mybatis config
 * Created by ustc-lezg on 05/01/2017.
 */
@Configuration
@EnableTransactionManagement
public class MybatisConfig {

    private static final String MYBATIS_CONFIG = "mybatis-config.xml";
    private static final String MAPPER_PATH = "/mapper/*.xml";
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/lezgdb?useUnicode=true&characterEncoding=utf8&useSSL=false";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    private static DataSource dataSource;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        if (dataSource != null) {
            return dataSource;
        }
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER_NAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaxActive(20);
        dataSource.setInitialSize(1);
        this.dataSource = dataSource;
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        SqlSessionFactoryBean ssfbean = new SqlSessionFactoryBean();
        ssfbean.setDataSource(dataSource());
        ssfbean.setTypeAliasesPackage("com.spring.hello.model");
        ssfbean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
        PathMatchingResourcePatternResolver pmrpResolver = new PathMatchingResourcePatternResolver();
        String mapperPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_PATH;
        try {
            ssfbean.setMapperLocations(pmrpResolver.getResources(mapperPath));
            return ssfbean.getObject();
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.spring.hello.mapper");
        return mapperScannerConfigurer;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }
}
