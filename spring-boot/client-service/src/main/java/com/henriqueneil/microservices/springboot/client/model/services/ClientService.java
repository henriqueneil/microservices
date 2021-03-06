package com.henriqueneil.microservices.springboot.client.model.services;

import com.henriqueneil.microservices.springboot.client.model.dto.Client;
import com.henriqueneil.microservices.springboot.client.model.enums.StringSearchCriteria;
import com.henriqueneil.microservices.springboot.client.model.exceptions.ClientNotFoundException;
import com.henriqueneil.microservices.springboot.client.persistence.interfaces.ClientDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

/**
 * {@link ClientService} is a class in charge for all the business logic around the Client Entity
 */
@Service
public class ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientDao clientDao;

    @Transactional(propagation = REQUIRED)
    public Client createClient(Client client) throws Exception {
        client.setId(UUID.randomUUID().toString());
        return clientDao.createClient(client);
    }

    @Transactional(propagation = REQUIRED)
    public Client findClientById(String id) throws ClientNotFoundException, Exception {
        Client client = clientDao.findClientById(id);
        if (client == null) {
            LOGGER.info(
                    String.format("No client with id [%s] was found and an exception will be thrown.",
                            id));
            throw new ClientNotFoundException(id);
        }
        return client;
    }

    @Transactional(propagation = REQUIRED)
    public List<Client> findClientByEmail(String email, StringSearchCriteria criteria) throws Exception {
        return clientDao.findClientByEmail(email, criteria);
    }

    @Transactional(propagation = REQUIRED)
    public List<Client> findClientByName(String name, StringSearchCriteria criteria) throws Exception {
        return clientDao.findClientByName(name, criteria);
    }

    @Transactional(propagation = REQUIRED)
    public Client updateClient(Client client) throws ClientNotFoundException, Exception {
        Client searchClient = findClientById(client.getId());
        if (searchClient == null) {
            LOGGER.info(
                    String.format("No client with id [%s] was found and an exception will be thrown.",
                            client.getId()));
            throw new ClientNotFoundException(client.getId());
        }
        return clientDao.updateClient(client);
    }

    @Transactional(propagation = REQUIRED)
    public void deleteClient(String id) throws ClientNotFoundException, Exception {
        Client client = findClientById(id);
        if (client == null) {
            LOGGER.info(
                    String.format("No client with id [%s] was found and an exception will be thrown.",
                            id));
            throw new ClientNotFoundException(client.getId());
        }
        clientDao.deleteClient(client);
    }
}
