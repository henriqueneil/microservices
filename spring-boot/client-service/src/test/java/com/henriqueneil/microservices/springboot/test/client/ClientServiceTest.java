package com.henriqueneil.microservices.springboot.test.client;

import com.henriqueneil.microservices.springboot.client.config.AppConfig;
import com.henriqueneil.microservices.springboot.client.model.dto.Client;
import com.henriqueneil.microservices.springboot.client.model.exceptions.ClientNotFoundException;
import com.henriqueneil.microservices.springboot.client.model.services.ClientService;
import com.henriqueneil.microservices.springboot.test.config.EmbeddedDatabaseConfig;
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

    @Before
    public void setUpTest() {
        service = context.getBean(ClientService.class);
    }

    @Test
    public void test_whenAValidClient_thenShouldReturnSuccess() throws Exception {

        Client client = new Client();
        client.setEmail("email@email.com");
        client.setName("Client Name");

        ClientService service = context.getBean(ClientService.class);
        Client createdClient = service.createClient(client);
        LOGGER.info(String.format("The client was created with id [%s]", createdClient.getId()));
    }

    @Test
    public void test_whenSearchUsingExistingId_thenShouldReturnClient() throws Exception {
        Client client = service.findClientById(DEFAULT_CLIENT_ID);
        LOGGER.info(String.format("The client with id [%s] has been found in the database.",
                client.getId()));
        assertNotNull(client);
    }

    @Test
    public void test_whenSearchUsingNonExistingId_thenShouldReturnNull() throws Exception {
        Client client = service.findClientById("Nah");
        LOGGER.info("The client object is " + client);
        assertNull(client);
    }

    @Test
    public void test_whenSearchByEmailContains_thenShouldReturnList() throws Exception {
        List<Client> clientList = service.findClientByEmail("contains.com", CONTAINS);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(3, clientList.size());
    }

    @Test
    public void test_whenSearchByEmailContainsNonExistingEmail_thenShouldReturnEmptyList() throws Exception {
        List<Client> clientList = service.findClientByEmail("xxx.com", CONTAINS);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(0, clientList.size());
    }

    @Test
    public void test_whenSearchByEmailStartsWith_thenShouldReturnList() throws Exception {
        List<Client> clientList = service.findClientByEmail("startswith", STARTS_WITH);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(3, clientList.size());
    }

    @Test
    public void test_whenSearchByEmailStartsWithNinExistingEmail_thenShouldReturnEmptyList() throws Exception {
        List<Client> clientList = service.findClientByEmail("XXX", STARTS_WITH);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(0, clientList.size());
    }

    @Test
    public void test_whenSearchByEmailEndsWith_thenShouldReturnList() throws Exception {
        List<Client> clientList = service.findClientByEmail("endswith", ENDS_WITH);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(3, clientList.size());
    }

    @Test
    public void test_whenSearchByEmailEndsWithNonExistinEmail_thenShouldReturnEmptyList() throws Exception {
        List<Client> clientList = service.findClientByEmail("XXX", ENDS_WITH);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(0, clientList.size());
    }

    @Test
    public void test_whenSearchByNameStartsWith_thenShouldReturnList() throws Exception {
        List<Client> clientList = service.findClientByName("StartsWith", STARTS_WITH);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(3, clientList.size());
    }

    @Test
    public void test_whenSearchByNameStartsWithNonExistingName_thenShouldReturnList() throws Exception {
        List<Client> clientList = service.findClientByName("XXX", STARTS_WITH);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(0, clientList.size());
    }

    @Test
    public void test_whenSearchByNameEndsWith_thenShouldReturnList() throws Exception {
        List<Client> clientList = service.findClientByName("EndsWith", ENDS_WITH);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(3, clientList.size());
    }

    @Test
    public void test_whenSearchByNameEndsWithNonExistingName_thenShouldReturnEmptyList() throws Exception {
        List<Client> clientList = service.findClientByName("XXX", ENDS_WITH);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(0, clientList.size());
    }

    @Test
    public void test_whenSearchByNameContains_thenShouldReturnList() throws Exception {
        List<Client> clientList = service.findClientByName("Contains", CONTAINS);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(3, clientList.size());
    }

    @Test
    public void test_whenSearchByNameContainsWithNonExistingName_thenShouldReturnEmptyList() throws Exception {
        List<Client> clientList = service.findClientByName("XXX", CONTAINS);
        LOGGER.info(String.format(MESSAGE_RETURNED_ITEMS, clientList.size()));
        assertEquals(0, clientList.size());
    }

    @Test
    public void test_whenUpdateAnExistingClient_thenShouldReturnUpdatedClient() throws Exception {
        try {
            Client client = new Client();
            client.setId("AADE6A2D-9424-4D83-8F31-5A96D501A4CA");
            client.setName("Update Client Test 19 Updated");
            client.setEmail("update@update.com.updated");

            Client updatedClient = service.updateClient(client);
            LOGGER.info("Updated client " + updatedClient.getName());
        } catch (ClientNotFoundException clientNotFoundException) {
            LOGGER.error("An exception happened.", clientNotFoundException);
            fail();
        }
    }

    @Test(expected = ClientNotFoundException.class)
    public void test_whenUpdateANonExistingClient_thenShouldReturnException()
            throws ClientNotFoundException, Exception {
        Client client = new Client();
        client.setId("XXX");
        client.setName("Update Client Test 19 Updated");
        client.setEmail("update@update.com.updated");

        Client updatedClient = service.updateClient(client);
        LOGGER.info("Updated client " + updatedClient.getName());
    }
}
