package com.henriqueneil.microservices.springboot.client;

import com.henriqueneil.microservices.springboot.client.config.AppConfig;
import com.henriqueneil.microservices.springboot.client.config.DatabaseConfig;
import com.henriqueneil.microservices.springboot.client.controller.v1.ClientController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * {@link Application} is the class in charge to start the Spring Boot container.
 *  - It is very important exclude {@link HibernateJpaAutoConfiguration} from the {@link EnableAutoConfiguration} annotation.
 * If it is removed a class cast exception occurs during database access.
 *  - {@link ClientController} sets up the REST service.
 *  - {@link AppConfig} sets up the basic configurations for the application.
 *  - {@link DatabaseConfig} sets up the database pool connection.
 */
@SpringBootApplication
@EnableSwagger2
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackageClasses = {ClientController.class, AppConfig.class, DatabaseConfig.class})
public class Application {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(Application.class);
    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Spring Boot Services")
                .apiInfo(newApiInfo())
                .select().paths(PathSelectors.regex("/client-services.*"))
                .build();
    }

    private ApiInfo newApiInfo() {
        return new ApiInfoBuilder().title("Spring Boot - Client Service")
                .description("This services is responsible for managing the clients.")
                .termsOfServiceUrl("http://www.henriqueneil.com")
                .license("Apache License Version 2.0")
                .licenseUrl("http://www.henriqueneil.com")
                .version("2.0")
                .build();
    }

}
