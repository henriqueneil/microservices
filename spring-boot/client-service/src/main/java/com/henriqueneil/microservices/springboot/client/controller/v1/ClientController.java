package com.henriqueneil.microservices.springboot.client.controller.v1;

import com.henriqueneil.microservices.springboot.client.controller.exceptions.BusinessException;
import com.henriqueneil.microservices.springboot.client.controller.exceptions.ServiceException;
import com.henriqueneil.microservices.springboot.client.model.dto.Client;
import com.henriqueneil.microservices.springboot.client.model.exceptions.ClientNotFoundException;
import com.henriqueneil.microservices.springboot.client.model.services.ClientService;
import com.henriqueneil.microservices.springboot.client.model.util.JsonUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * {@link ClientController} is the class in charge to control all the request for the Client Entity.
 */
@RestController
public class ClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @ApiOperation(value = "Operation that adds a new client to the system.", nickname = "Create Client",
            response = Client.class)
    @RequestMapping(method = RequestMethod.POST, path = "/client-services/V1/create",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client has been created!", response = Client.class),
            @ApiResponse(code = 201, message = "The request was received but there is business error",
                    response = BusinessException.class),
            @ApiResponse(code = 500, message = "Failure", response = ServiceException.class)})
    public String createClient(@RequestBody Client client) {

        try {
            LOGGER.info("Request for creating a new client was received!");
            Client createdClient = clientService.createClient(client);
            return JsonUtils.fromObjectToJson(createdClient);
        } catch (Exception exception) {
            LOGGER.error("An exception happened.", exception);
            ServiceException serviceException =
                    new ServiceException(exception.getClass().getCanonicalName(),
                            "SE01", exception.getMessage());
            return serviceException.toString();
        }
    }

    @ApiOperation(value = "Operation that get the client information based on a given id.",
            nickname = "Get Client",
            response = Client.class)
    @RequestMapping(method = RequestMethod.GET, path = "/client-services/V1/get/{id}",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client information was collected successfully",
                    response = Client.class),
            @ApiResponse(code = 201, message = "The request was received but there is business error",
                    response = BusinessException.class),
            @ApiResponse(code = 500, message = "Failure", response = ServiceException.class)})
    public String getClient(@PathVariable String id) {

        try {
            LOGGER.info(String.format("A request for getting a client information with id [%s] was received!", id));
            return JsonUtils.fromObjectToJson(clientService.findClientById(id));
        } catch (ClientNotFoundException clientNotFoundException) {
            LOGGER.error("An exception happened.", clientNotFoundException);
            ServiceException serviceException =
                    new ServiceException(clientNotFoundException.getClass().getCanonicalName(),
                            "SE01", clientNotFoundException.getMessage());
            return serviceException.toString();
        } catch (Exception exception) {
            LOGGER.error("An exception happened.", exception);
            ServiceException serviceException =
                    new ServiceException(exception.getClass().getCanonicalName(),
                            "SE01", exception.getMessage());
            return serviceException.toString();
        }
    }

    @ApiOperation(value = "Operation that updates an existing client in system.",
            nickname = "Update Client",
            response = Client.class)
    @RequestMapping(method = RequestMethod.POST, path = "/client-services/V1/update",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client updated successfully!", response = Client.class),
            @ApiResponse(code = 201, message = "The request was received but there is business error",
                    response = BusinessException.class),
            @ApiResponse(code = 500, message = "Failure", response = ServiceException.class)})
    public String updateClient(@RequestBody Client client) {

        try {
            LOGGER.info("Request for updating a client was received!");
            return JsonUtils.fromObjectToJson(clientService.updateClient(client));
        } catch (ClientNotFoundException clientNotFoundException) {
            LOGGER.error("An exception happened.", clientNotFoundException);
            ServiceException serviceException =
                    new ServiceException(clientNotFoundException.getClass().getCanonicalName(),
                            "SE01", clientNotFoundException.getMessage());
            return serviceException.toString();
        } catch (Exception exception) {
            LOGGER.error("An exception happened.", exception);
            ServiceException serviceException =
                    new ServiceException(exception.getClass().getCanonicalName(),
                            "SE01", exception.getMessage());
            return serviceException.toString();
        }
    }

    @ApiOperation(value = "Operation that deletes an existing client from the system.",
            nickname = "Delete Client",
            response = Client.class)
    @RequestMapping(method = RequestMethod.GET, path = "/client-services/V1/delete/{id}",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client Deleted successfully!", response = Client.class),
            @ApiResponse(code = 201, message = "The request was received but there is business error",
                    response = BusinessException.class),
            @ApiResponse(code = 500, message = "Failure", response = ServiceException.class)})
    public String deleteClient(@PathVariable String id) {

        try {
            LOGGER.info(String.format("Request for deleting a client with id [%s] was received!", id));
            clientService.deleteClient(id);
            return String.format("{Client with id [%s] was successfully deleted.}", id);
        } catch (ClientNotFoundException clientNotFoundException) {
            LOGGER.error("An exception happened.", clientNotFoundException);
            ServiceException serviceException =
                    new ServiceException(clientNotFoundException.getClass().getCanonicalName(),
                            "SE01", clientNotFoundException.getMessage());
            return serviceException.toString();
        } catch (Exception exception) {
            LOGGER.error("An exception happened.", exception);
            ServiceException serviceException =
                    new ServiceException(exception.getClass().getCanonicalName(),
                            "SE01", exception.getMessage());
            return serviceException.toString();
        }
    }
}
