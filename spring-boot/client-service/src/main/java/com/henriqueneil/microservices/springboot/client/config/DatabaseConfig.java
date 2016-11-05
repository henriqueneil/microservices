package com.henriqueneil.microservices.springboot.client.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Database configuration class
 */
@Component
@PropertySource("classpath:config/database/database.properties")
public class DatabaseConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);

    @Value("${database.driver.name}")
    private String driverName;

    @Value("${database.url}")
    private String databaseUrl;

    @Value("${database.user.name}")
    private String username;

    @Value("${database.user.password}")
    private String password;

    @Bean(name = "clientDataSource", destroyMethod = "close")
    public BasicDataSource createDataSource() {

        LOGGER.info(String.format("Going to start the database using driver [%s], url [%s], username [%s]",
                driverName, databaseUrl, username));
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }
}
