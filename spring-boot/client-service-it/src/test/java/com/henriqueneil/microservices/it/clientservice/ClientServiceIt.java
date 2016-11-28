package com.henriqueneil.microservices.it.clientservice;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import com.henriqueneil.microservices.it.config.TestConfig;
import com.henriqueneil.microservices.it.validations.CreateClientValidation;
import com.henriqueneil.microservices.it.validations.GetClientValidation;
import com.henriqueneil.microservices.it.validations.UpdateClientValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import javax.sql.DataSource;

/**
 * Integration tests for Client Service
 */
@ContextConfiguration(classes = {TestConfig.class})
public class ClientServiceIt extends TestNGCitrusTestDesigner {

    @Autowired
    private HttpClient httpCreateClient;
    @Autowired
    private HttpClient httpGetClient;

    @Autowired
    private HttpClient httpUpdateClient;

    @Autowired
    private HttpClient httpDeleteClient;

    @Autowired
    private DataSource clientDataSource;

    @Value("${query.count.client.by.id}")
    private String queryCountClientById;

    @Value("${query.count.client.by.email}")
    private String queryCountClientByEmail;

    @Value("${query.client.by.id}")
    private String queryClientById;

    private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
    private static final String FIELD_COUNTER = "COUNTER";

    private static final String VARIABLE_CLIENT_NAME = "clientName";
    private static final String VARIABLE_CLIENT_EMAIL = "clientEmail";
    private static final String VARIABLE_CLIENT_ID = "clientId";

    private CreateClientValidation createClientValidation = new CreateClientValidation();
    private GetClientValidation getClientValidation = new GetClientValidation();
    private UpdateClientValidation updateClientValidation = new UpdateClientValidation();

    private ClassPathResource request = new ClassPathResource("templates/request.json");
    private ClassPathResource updateRequest = new ClassPathResource("templates/update-request.json");
    private ClassPathResource getResponse = new ClassPathResource("templates/get-response.json");

    @Test
    @CitrusTest
    public void test_whenRequestingCreateNewClient_thenShouldCreateClientSuccessfully() throws Exception {

        String email = "clientinserttest001@email.com";
        variable(VARIABLE_CLIENT_NAME, "Client Insert Test 001");
        variable(VARIABLE_CLIENT_EMAIL, email);

        query(clientDataSource)
                .statement(String.format(queryCountClientByEmail, email))
                .validate(FIELD_COUNTER, "0");

        http().client(httpCreateClient).send()
                .post()
                .contentType(CONTENT_TYPE_APPLICATION_JSON)
                .payload(request);

        http().client(httpCreateClient)
                .receive().response(HttpStatus.OK).messageType(MessageType.JSON)
                .payload(request)
                .validator(createClientValidation);

        query(clientDataSource)
                .statement(String.format(queryCountClientByEmail, email))
                .validate(FIELD_COUNTER, "1");
    }

    @Test
    @CitrusTest
    public void test_whenSearchClientByExistingId_thenShouldReturnClientInformation() throws Exception {

        variable(VARIABLE_CLIENT_ID, "6a13534c-da14-4916-98c5-26da758953b3");
        variable(VARIABLE_CLIENT_NAME, "Get Client Test 1");
        variable(VARIABLE_CLIENT_EMAIL, "getclient1@get.com");

        http().client(httpGetClient).send()
                .get().contentType(CONTENT_TYPE_APPLICATION_JSON)
                .path("/6a13534c-da14-4916-98c5-26da758953b3");

        http().client(httpGetClient)
                .receive().response(HttpStatus.OK).messageType(MessageType.JSON)
                .payload(getResponse)
                .validator(getClientValidation);
    }

    @Test
    @CitrusTest
    public void test_whenUpdatingAnExistingClient_thenShouldReturnUpdatedClient() throws Exception {

        String clientId = "aade6a2d-9424-4d83-8f31-5a96d501a4ca";
        String clientName = "Update Client Test 1";
        String clientEmail = "update@update.com";

        query(clientDataSource)
                .statement(String.format(queryClientById, clientId))
                .validate("NAME", clientName)
                .validate("EMAIL", clientEmail);

        variable(VARIABLE_CLIENT_ID, clientId);
        variable(VARIABLE_CLIENT_NAME, clientName + " Updated");
        variable(VARIABLE_CLIENT_EMAIL, clientEmail + ".updated");

        http().client(httpUpdateClient).send()
                .post()
                .contentType(CONTENT_TYPE_APPLICATION_JSON)
                .payload(updateRequest);

        http().client(httpUpdateClient)
                .receive().response(HttpStatus.OK).messageType(MessageType.JSON)
                .payload(updateRequest)
                .validator(updateClientValidation);

        query(clientDataSource)
                .statement(String.format(queryClientById, clientId))
                .validate("NAME", clientName + " Updated")
                .validate("EMAIL", clientEmail + ".updated");
    }

    @Test
    @CitrusTest
    public void test_whenDeletingAnExistingClient_thenShouldReturnSuccessMessage() throws Exception {

        String query = String.format(queryCountClientById, "7804b871-626c-40bf-8aa3-459d52cc1331");
        query(clientDataSource)
                .statement(query)
                .validate(FIELD_COUNTER, "1");

        http().client(httpDeleteClient).send()
                .get().contentType(CONTENT_TYPE_APPLICATION_JSON)
                .path("/7804b871-626c-40bf-8aa3-459d52cc1331");

        http().client(httpDeleteClient)
                .receive().response(HttpStatus.OK).messageType(MessageType.JSON);

        query(clientDataSource)
                .statement(query)
                .validate(FIELD_COUNTER, "0");
    }
}
