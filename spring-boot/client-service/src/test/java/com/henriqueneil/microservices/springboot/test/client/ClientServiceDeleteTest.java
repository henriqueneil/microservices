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

import java.util.List;

import static com.henriqueneil.microservices.springboot.client.model.enums.StringSearchCriteria.CONTAINS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Client Service Test Class for Delete Operation
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmbeddedDatabaseConfig.class, AppConfig.class})
public class ClientServiceDeleteTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceDeleteTest.class);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ClientService service;

    private static final String DELETE_CLIENT_ID = "7804B871-626C-40BF-8AA3-459D52CC1331";

    /**
     * Test case for successfully deletion of a client.
     * As the name is unique for unit tests, it will be used as parameter as findById throws an exception case the client does not exist.
     * Check the file database/scripts/inserts.sql
     * When: Using an existing client id.
     * Then: The client should be deleted.
     * @throws Exception Any unexpected exception during the test.
     */
    @Test
    public void test_whenDeleteAnExistingClient_thenClientShouldBeDeleted() throws Exception {
        try {
            String clientName = "Delete Client Test 22";
            List<Client> listBefore = service.findClientByName(clientName, CONTAINS);
            service.deleteClient(DELETE_CLIENT_ID);
            List<Client> listAfter = service.findClientByName(clientName, CONTAINS);

            assertEquals(1, listBefore.size());
            assertEquals(0, listAfter.size());
        } catch (ClientNotFoundException clientNotFoundException) {
            LOGGER.info("An exception happened during the test.", clientNotFoundException);
            fail();
        }
    }

    /**
     * Test case for client deletion failure scenario.
     * @throws ClientNotFoundException The expected exception
     * @throws Exception Any unexpected exception during the test.
     */
    @Test(expected = ClientNotFoundException.class)
    public void test_whenDeleteANonExistingClient_thenShouldThrownAnException()
            throws ClientNotFoundException, Exception {
        service.deleteClient("XXX");
    }
}
