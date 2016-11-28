package com.henriqueneil.microservices.springboot.test.client;

import com.henriqueneil.microservices.springboot.client.config.AppConfig;
import com.henriqueneil.microservices.springboot.client.model.dto.Client;
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
import static com.henriqueneil.microservices.springboot.client.model.enums.StringSearchCriteria.ENDS_WITH;
import static com.henriqueneil.microservices.springboot.client.model.enums.StringSearchCriteria.STARTS_WITH;
import static org.junit.Assert.assertEquals;

/**
 * Client Service Test Class for Search By Name
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmbeddedDatabaseConfig.class, AppConfig.class})
public class ClientServiceSearchByNameTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceSearchByNameTest.class);

    private static final String MESSAGE_RETURNED_ITEMS = "The query returned [%d] items.";

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ClientService service;

    /**
     * Test case for client search by name that starts with a given string.
     * Check the file database/scripts/inserts.sql
     * When: Using a string that exists in the name column in the database.
     * Then: Should return a list containing 3 items.
     * @throws Exception Any unexpected exception during the test.
     */
    @Test
    public void test_whenSearchByNameStartsWith_thenShouldReturnList() throws Exception {
        List<Client> clientList = service.findClientByName("StartsWith", STARTS_WITH);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(3, clientList.size());
    }

    /**
     * Test case for client search by name that does not start with a given string.
     * Check the file database/scripts/inserts.sql
     * When: Using a string that does not start with in the name column in the database.
     * Then: Should return a list containing 3 items.
     * @throws Exception Any unexpected exception during the test.
     */
    @Test
    public void test_whenSearchByNameStartsWithNonExistingName_thenShouldReturnList() throws Exception {
        List<Client> clientList = service.findClientByName("XXX", STARTS_WITH);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(0, clientList.size());
    }

    /**
     * Test case for client search by name that ends with a given string.
     * Check the file database/scripts/inserts.sql
     * When: Using a string that exists in the name column in the database.
     * Then: Should return a list containing 3 items.
     * @throws Exception Any unexpected exception during the test.
     */
    @Test
    public void test_whenSearchByNameEndsWith_thenShouldReturnList() throws Exception {
        List<Client> clientList = service.findClientByName("EndsWith", ENDS_WITH);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(3, clientList.size());
    }

    /**
     * Test case for client search by name that does not end with a given string.
     * Check the file database/scripts/inserts.sql
     * When: Using a string that does not end with in the name column in the database.
     * Then: Should return a list containing 3 items.
     * @throws Exception Any unexpected exception during the test.
     */
    @Test
    public void test_whenSearchByNameEndsWithNonExistingName_thenShouldReturnEmptyList() throws Exception {
        List<Client> clientList = service.findClientByName("XXX", ENDS_WITH);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(0, clientList.size());
    }

    /**
     * Test case for client search by name that contains a given string in any position.
     * Check the file database/scripts/inserts.sql
     * When: Using a string that exists in the name column in the database.
     * Then: Should return a list containing 3 items.
     * @throws Exception Any unexpected exception during the test.
     */
    @Test
    public void test_whenSearchByNameContains_thenShouldReturnList() throws Exception {
        List<Client> clientList = service.findClientByName("Contains", CONTAINS);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(3, clientList.size());
    }

    /**
     * Test case for client search by name that does not contain a given string in any position.
     * Check the file database/scripts/inserts.sql
     * When: Using a string that does not exist in the name column in the database.
     * Then: Should return a list containing 3 items.
     * @throws Exception Any unexpected exception during the test.
     */
    @Test
    public void test_whenSearchByNameContainsWithNonExistingName_thenShouldReturnEmptyList() throws Exception {
        List<Client> clientList = service.findClientByName("XXX", CONTAINS);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(0, clientList.size());
    }
}
