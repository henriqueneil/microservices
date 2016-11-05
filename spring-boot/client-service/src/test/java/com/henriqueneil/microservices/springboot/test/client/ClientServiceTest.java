package com.henriqueneil.microservices.springboot.test.client;

import com.henriqueneil.microservices.springboot.client.config.AppConfig;
import com.henriqueneil.microservices.springboot.client.model.dto.Client;
import com.henriqueneil.microservices.springboot.client.model.exceptions.ClientNotFoundException;
import com.henriqueneil.microservices.springboot.client.model.services.ClientService;
import com.henriqueneil.microservices.springboot.test.config.EmbeddedDatabaseConfig;
import com.henriqueneil.microservices.springboot.test.fixtures.ClientFixture;
import org.junit.Before;
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
import static org.junit.Assert.*;

/**
 * Test Class for Client
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmbeddedDatabaseConfig.class, AppConfig.class})
public class ClientServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceTest.class);
    private static final String MESSAGE_RETURNED_ITEMS = "The query returned [%d] items.";

    @Autowired
    private ApplicationContext context;

    private ClientService service;

    private static final String DEFAULT_CLIENT_ID = "A4268B95-BF1B-41F6-8F53-93C61AC9E34A";
    private static final String DELETE_CLIENT_ID = "7804B871-626C-40BF-8AA3-459D52CC1331";

    @Before
    public void setUpTest() {
        service = context.getBean(ClientService.class);
    }

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
    public void test_whenSearchByEmailEndsWithNonExistinEmail_thenShouldReturnEmptyList() throws Exception {
        List<Client> clientList = service.findClientByEmail("XXX", ENDS_WITH);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(0, clientList.size());
    }

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
