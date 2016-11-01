package com.henriqueneil.microservices.springboot.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Component;

/**
 * Embedded Database configuration
 */
@Component
public class EmbeddedDatabaseConfig {

    @Bean(name = "clientDatasource")
    public EmbeddedDatabase clientDatasource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase database = builder.setType(EmbeddedDatabaseType.HSQL)
                .addScripts("database/scripts/tables.sql",
                        "database/scripts/inserts.sql").build();
        return database;
    }
}
