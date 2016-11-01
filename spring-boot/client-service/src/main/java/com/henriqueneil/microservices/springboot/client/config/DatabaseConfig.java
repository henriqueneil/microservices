package com.henriqueneil.microservices.springboot.client.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Database configuration class
 */
@Component
public class DatabaseConfig {

    @Bean(name = "clientDatasource", destroyMethod = "close")
    public BasicDataSource createDatasource() {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:client");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        return dataSource;
    }
}
