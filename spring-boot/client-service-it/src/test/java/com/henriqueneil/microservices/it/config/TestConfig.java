package com.henriqueneil.microservices.it.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/**
 * Config class for test
 */
@Component
@Import({HttpConfig.class, DatabaseConfig.class})
@PropertySource("classpath:config/database/queries.properties")
public class TestConfig {

    /**
     * This method is necessary due to a known issue on Spring Framework that is not able to load the properties from @{@link PropertySource}.
     * @return PropertySourcesPlaceholderConfigurer
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
