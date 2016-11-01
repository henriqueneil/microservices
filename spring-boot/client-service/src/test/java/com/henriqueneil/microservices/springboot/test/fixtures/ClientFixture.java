package com.henriqueneil.microservices.springboot.test.fixtures;

import com.henriqueneil.microservices.springboot.client.model.dto.Client;

/**
 * Fixture class for Client Entity
 */
public class ClientFixture {

    /**
     * Fixture method that returns a Client object with basic data for insertion.
     * @return Client Client object with basic data.
     */
    public static Client fixtureClientBasicInsert() {
        Client client = new Client();
        client.setEmail("email@email.com");
        client.setName("Client Name");
        return client;
    }

    /**
     * Fixture method that returns a Client object with a valid id.
     * @return Client Client Object with a valid id.
     */
    public static Client fixtureClientWithValidId() {
        Client client = new Client();
        client.setId("AADE6A2D-9424-4D83-8F31-5A96D501A4CA");
        client.setName("Update Client Test 19 Updated");
        client.setEmail("update@update.com.updated");
        return client;
    }
    /**
     * Fixture method that returns a Client object with an invalid id.
     * @return Client The Client object with an invalid id.
     */
    public static Client fixtureClientWithInvalidId() {
        Client client = new Client();
        client.setId("XXX");
        client.setName("Update Client Test 19 Updated");
        client.setEmail("update@update.com.updated");
        return client;
    }
}
