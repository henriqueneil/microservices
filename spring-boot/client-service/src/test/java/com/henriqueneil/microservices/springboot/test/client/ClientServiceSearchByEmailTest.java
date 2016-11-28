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

import static com.henriqueneil.microservices.springboot.client.model.enums.StringSearchCriteria.*;
import static org.junit.Assert.assertEquals;

/**
 * Client Service Test Class for Search By Email Operation
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmbeddedDatabaseConfig.class, AppConfig.class})
public class ClientServiceSearchByEmailTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceSearchByEmailTest.class);

    private static final String MESSAGE_RETURNED_ITEMS = "The query returned [%d] items.";

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ClientService service;

    /**
     * Test case for client search by email that contains a string in any position.
     * Check the file database/scripts/inserts.sql
     * When: Using a string that exists in the email column in the database.
     * Then: Should return a list containing 3 items.
     * @throws Exception Any unexpected exception during the test.
     */
    @Test
    public void test_whenSearchByEmailContains_thenShouldReturnList() throws Exception {
        List<Client> clientList = service.findClientByEmail("contains.com", CONTAINS);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(3, clientList.size());
    }

    /**
     * Test case for client search by email that does not contain a string in any position.
     * Check the file database/scripts/inserts.sql
     * When: Using a string that does not exist in the email column in the database.
     * Then: Should return an empty list.
     * @throws Exception Any unexpected exception during the test.
     */
    @Test
    public void test_whenSearchByEmailContainsNonExistingEmail_thenShouldReturnEmptyList() throws Exception {
        List<Client> clientList = service.findClientByEmail("xxx.com", CONTAINS);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(0, clientList.size());
    }

    /**
     * Test case for client search by email that starts with a given string.
     * Check the file database/scripts/inserts.sql
     * When: Using a string that exists in the email column in the database.
     * Then: Should return a list containing 3 items.
     * @throws Exception Any unexpected exception during the test.
     */
    @Test
    public void test_whenSearchByEmailStartsWith_thenShouldReturnList() throws Exception {
        List<Client> clientList = service.findClientByEmail("startswith", STARTS_WITH);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(3, clientList.size());
    }

    /**
     * Test case for client search by email that does not starts with a given string.
     * Check the file database/scripts/inserts.sql
     * When: Using a string that does not start with in the email column in the database.
     * Then: Should return an empty list.
     * @throws Exception Any unexpected exception during the test.
     */
    @Test
    public void test_whenSearchByEmailStartsWithNinExistingEmail_thenShouldReturnEmptyList() throws Exception {
        List<Client> clientList = service.findClientByEmail("XXX", STARTS_WITH);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(0, clientList.size());
    }

    /**
     * Test case for client search by email that ends with a given string.
     * Check the file database/scripts/inserts.sql
     * When: Using a string that ends with in the email column in the database.
     * Then: Should return a list containing 3 items.
     * @throws Exception Any unexpected exception during the test.
     */
    @Test
    public void test_whenSearchByEmailEndsWith_thenShouldReturnList() throws Exception {
        List<Client> clientList = service.findClientByEmail("endswith", ENDS_WITH);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(3, clientList.size());
    }

    /**
     * Test case for client search by email that does not end with a given string.
     * Check the file database/scripts/inserts.sql
     * When: Using a string that does not end with in the email column in the database.
     * Then: Should return a list containing 3 items.
     * @throws Exception Any unexpected exception during the test.
     */
    @Test
    public void test_whenSearchByEmailEndsWithNonExistingEmail_thenShouldReturnEmptyList() throws Exception {
        List<Client> clientList = service.findClientByEmail("XXX", ENDS_WITH);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(0, clientList.size());
    }
}
