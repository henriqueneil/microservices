package com.henriqueneil.microservices.springboot.test.client;

import com.henriqueneil.microservices.springboot.client.config.AppConfig;
import com.henriqueneil.microservices.springboot.client.model.dto.Client;
import com.henriqueneil.microservices.springboot.client.model.exceptions.ClientNotFoundException;
import com.henriqueneil.microservices.springboot.client.model.services.ClientService;
import com.henriqueneil.microservices.springboot.test.config.EmbeddedDatabaseConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Client Service Test Class for Search By Id Operation
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmbeddedDatabaseConfig.class, AppConfig.class})
public class ClientServiceSearchByIdTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceSearchByIdTest.class);

    private static final String DEFAULT_CLIENT_ID = "A4268B95-BF1B-41F6-8F53-93C61AC9E34A";

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ClientService service;

    /**
     * Basic search test using the client id.
     * Check the file database/scripts/inserts.sql
     * When: Querying for a client using an existing id.
     * Then: Should return the client information based on this id.
     * @throws Exception Any unexpected exception during the test.
     */
    @Test
    public void test_whenSearchUsingExistingId_thenShouldReturnClient() throws Exception {
        try {
            Client client = service.findClientById(DEFAULT_CLIENT_ID);
            LOGGER.info(String.format("The client with id [%s] has been found in the database.",
                    client.getId()));
            assertNotNull(client);
            assertEquals(DEFAULT_CLIENT_ID, client.getId());
            assertEquals("Client 1", client.getName());
            assertEquals("client1@contains.com", client.getEmail());
        } catch (ClientNotFoundException clientNotFoundException) {
            LOGGER.info("An exception happened during the test.", clientNotFoundException);
            fail();
        }
    }

    /**
     * Basic search test using an non existing client id.
     * @throws ClientNotFoundException Expected exception.
     * @throws Exception Any unexpected exception during the test.
     */
    @Test(expected = ClientNotFoundException.class)
    public void test_whenSearchUsingNonExistingId_thenShouldReturnException() throws ClientNotFoundException, Exception {
        service.findClientById("Nah");
    }
}
