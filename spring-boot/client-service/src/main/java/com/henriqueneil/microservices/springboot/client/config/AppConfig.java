package com.henriqueneil.microservices.springboot.client.config;

import com.henriqueneil.microservices.springboot.client.model.dto.Client;
import com.henriqueneil.microservices.springboot.client.model.services.ClientService;
import com.henriqueneil.microservices.springboot.client.persistence.impl.ClientDaoImpl;
import com.henriqueneil.microservices.springboot.client.persistence.interfaces.ClientDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Application Configuration Class
 */
@Component
@EnableTransactionManagement
public class AppConfig {

    @Autowired
    ApplicationContext context;

    @Bean(name = "clientDao")
    public ClientDao createClientDao() {
        return new ClientDaoImpl();
    }

    @Bean(name = "clientService")
    public ClientService clientService() {
        return new ClientService();
    }

    @Bean(name = "sessionFactory")
    public SessionFactory sessionFactory() {

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        hibernateProperties.put("hibernate.show_sql", "true");

        DataSource dataSource = context.getBean(DataSource.class);
        LocalSessionFactoryBuilder factoryBuilder = new LocalSessionFactoryBuilder(dataSource);
        factoryBuilder.addAnnotatedClasses(Client.class)
                .addProperties(hibernateProperties);

        SessionFactory sessionFactory = factoryBuilder.buildSessionFactory();
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
