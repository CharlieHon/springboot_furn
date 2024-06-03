package com.charlie.furn.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class DruidDataSourceConfig {

    /**
     * 为什么配置/注入指定的数据源，就替换了默认的数据源？
     * 在SpringBoot切换数据源时讲过
     */
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        // class com.alibaba.druid.pool.DruidDataSource
        //log.info("数据源={}", druidDataSource.getClass());
        return druidDataSource;
    }

}
