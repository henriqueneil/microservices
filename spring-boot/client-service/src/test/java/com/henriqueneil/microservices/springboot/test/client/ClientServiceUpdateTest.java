package com.henriqueneil.microservices.springboot.test.client;

import com.henriqueneil.microservices.springboot.client.config.AppConfig;
import com.henriqueneil.microservices.springboot.client.model.dto.Client;
import com.henriqueneil.microservices.springboot.client.model.exceptions.ClientNotFoundException;
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

import static org.junit.Assert.*;

/**
 * Client Service Test Class for Update Operation
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmbeddedDatabaseConfig.class, AppConfig.class})
public class ClientServiceUpdateTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceUpdateTest.class);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ClientService service;

    /**
     * Test case for updating an existing client in the database.
     * Check the file database/scripts/inserts.sql
     * When: Using a client that exists in the database.
     * Then: The client should be updated and return the object with updated data.
     * @throws Exception Any unexpected exception during the test.
     */
    @Test
    public void test_whenUpdateAnExistingClient_thenShouldReturnUpdatedClient() throws Exception {
        try {
            Client client = ClientFixture.fixtureClientWithValidId();
            Client oldClient = service.findClientById(client.getId());

            Client updatedClient = service.updateClient(client);
            LOGGER.info(String.format("The client name was updated from [%s] to [%s] and the email from [%s] to [%s].",
                    oldClient.getName(), updatedClient.getName(), oldClient.getEmail(), updatedClient.getEmail()));

            assertEquals(oldClient.getId(), updatedClient.getId());
            assertNotEquals(oldClient.getName(), updatedClient.getName());
            assertNotEquals(oldClient.getEmail(), updatedClient.getEmail());
        } catch (ClientNotFoundException clientNotFoundException) {
            LOGGER.error("An exception happened.", clientNotFoundException);
            fail();
        }
    }

    /**
     * Test case for a failing update client.
     * Check the file database/scripts/inserts.sql
     * When: Trying to update a client using an id that does not exist.
     * Then: Should thrown an exception of type {@link ClientNotFoundException}
     * @throws ClientNotFoundException The expected exception.
     * @throws Exception Any unexpected exception during the test.
     */
    @Test(expected = ClientNotFoundException.class)
    public void test_whenUpdateANonExistingClient_thenShouldReturnException()
            throws ClientNotFoundException, Exception {
        Client client = ClientFixture.fixtureClientWithInvalidId();
        service.updateClient(client);
    }
}
