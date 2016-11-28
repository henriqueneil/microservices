package com.henriqueneil.microservices.springboot.test.client;

import com.henriqueneil.microservices.springboot.client.config.AppConfig;
import com.henriqueneil.microservices.springboot.client.model.dto.Client;
import com.henriqueneil.microservices.springboot.client.model.services.ClientService;
import com.henriqueneil.microservices.springboot.test.config.EmbeddedDatabaseConfig;
import com.henriqueneil.microservices.springboot.test.fixtures.ClientFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Client Service Test Class for Create Operation
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmbeddedDatabaseConfig.class, AppConfig.class})
public class ClientServiceCreateTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceCreateTest.class);

    @Autowired
    private ApplicationContext context;

    /**
     * Basic insert test.
     * When: Sending a valid client information.
     * Then: The client must be inserted in the database.
     * @throws Exception Any unexpected exception during the test.
     */
    @Test
    public void test_whenAValidClient_thenShouldReturnSuccess() throws Exception {
        Client client = ClientFixture.fixtureClientBasicInsert();
        ClientService service = context.getBean(ClientService.class);
        Client createdClient = service.createClient(client);
        LOGGER.info(String.format("The client was created with id [%s]", createdClient.getId()));
        assertNotNull(createdClient);
    }
}
