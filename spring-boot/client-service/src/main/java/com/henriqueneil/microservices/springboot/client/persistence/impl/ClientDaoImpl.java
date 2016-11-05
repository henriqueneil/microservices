package com.henriqueneil.microservices.springboot.client.persistence.impl;

import com.henriqueneil.microservices.springboot.client.model.dto.Client;
import com.henriqueneil.microservices.springboot.client.model.enums.StringSearchCriteria;
import com.henriqueneil.microservices.springboot.client.persistence.interfaces.ClientDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Implementation of DAO class for Client Entity
 */
@Repository
public class ClientDaoImpl implements ClientDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientDaoImpl.class);
    private static final String PERCENTAGE_SYMBOL = "%";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_EMAIL = "email";

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public Client createClient(final Client client) throws Exception {
        getSession().persist(client);
        return client;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Client updateClient(final Client client) throws Exception {
        return (Client) getSession().merge(client);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Client findClientById(String id) throws Exception {
        return getSession().get(Client.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Client> findClientByName(final String name, StringSearchCriteria criteria) throws Exception {
        String search = formatStringSearch(name, criteria);
        return findClientBy(FIELD_NAME, search);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Client> findClientByEmail(final String email, StringSearchCriteria criteria) throws Exception {
        String search = formatStringSearch(email, criteria);
        return findClientBy(FIELD_EMAIL, search);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteClient(final Client client) throws Exception {
        getSession().delete(client);
    }

    /**
     * Private method that get the current session in a single way.
     * @return Session The current session
     */
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Private method that returns a formatted string based on criteria.
     * @param value The value to be formatted.
     * @param criteria The formatting criteria.
     * @return String The string formatted for searching.
     */
    private String formatStringSearch(String value, StringSearchCriteria criteria) {
        StringBuilder search = new StringBuilder();
        switch (criteria) {
            case STARTS_WITH:
                search.append(value).append(PERCENTAGE_SYMBOL);
                break;
            case ENDS_WITH:
                search.append(PERCENTAGE_SYMBOL).append(value);
                break;
            case CONTAINS:
                search.append(PERCENTAGE_SYMBOL).append(value)
                        .append(PERCENTAGE_SYMBOL);
                break;
            default:
                search.append(value);
        }
        return search.toString();
    }

    /**
     * Private method that find a client by a given field in a single way.
     * @param field Field to be searched by.
     * @param value Value that the field must match.
     * @return List List of client in the result.
     * @throws Exception Any exception thrown during the method call.
     */
    private List<Client> findClientBy(String field, String value) throws Exception {
        try {
            CriteriaBuilder builder = getSession().getCriteriaBuilder();
            CriteriaQuery<Client> criteriaQuery = builder.createQuery(Client.class);
            Root<Client> rootClient = criteriaQuery.from(Client.class);

            Predicate predicate = builder.like(rootClient.get(field), value);
            criteriaQuery.where(builder.and(predicate));

            return getSession().createQuery(criteriaQuery).getResultList();
        } catch (Exception exception) {
            LOGGER.error(String.format("An exception happened while searching the client by [%s].", field),
                    exception);
            throw exception;
        }
    }
}
