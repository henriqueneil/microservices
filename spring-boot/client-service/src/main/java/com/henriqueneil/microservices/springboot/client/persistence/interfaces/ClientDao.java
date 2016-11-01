package com.henriqueneil.microservices.springboot.client.persistence.interfaces;

import com.henriqueneil.microservices.springboot.client.model.dto.Client;
import com.henriqueneil.microservices.springboot.client.model.enums.StringSearchCriteria;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Interface for Client entity
 */
public interface ClientDao {

    /**
     * Method that adds a new client to the database.
     * @param client Object containing the client information to be added to the database.
     * @return Client Object containing the new client information.
     * @throws Exception Any exception thrown during the method call.
     */
    Client createClient(Client client) throws Exception;

    /**
     * Method that update an existing client in the database.
     * @param client Object containing the client information to be updated.
     * @return Client Object containing the updated client
     * @throws Exception Any exception thrown during the method call.
     */
    Client updateClient(Client client) throws Exception;

    /**
     * Method that search for a Client based on a given id.
     * @param id The client id.
     * @return Client The object containing the information about the client.
     * @throws Exception Any exception thrown during the method call.
     */
    public Client findClientById(String id) throws Exception;

    /**
     * Method that lists the clients based on a given name.
     * @param name String that contains the client name to be searched.
     * @param criteria Define the criteria for searching, if the string must start with, end with or contain the string sequence.
     * @return List of {@link Client} object as result of search.
     * @throws Exception Any exception thrown during the method call.
     */
    List<Client> findClientByName(String name, StringSearchCriteria criteria) throws Exception;

    /**
     * Method that lists the clients based on a given email.
     * @param email String that contains the client email to be searched.
     * @param criteria Define the criteria for searching, if the string must start with, end with or contain the string sequence.
     * @return List of {@link Client} object as result of search.
     * @throws Exception Any exception thrown during the method call.
     */
    List<Client> findClientByEmail(String email, StringSearchCriteria criteria) throws Exception;

    /**
     * Method that delete a client from the database based on a given id.
     * @param id The client id.
     * @throws Exception Any exception thrown during the method call.
     */
    void deleteClient(String id) throws Exception;
}
