package com.henriqueneil.microservices.springboot.client.model.exceptions;

/**
 * Exception that handles a client not found.
 */
public class ClientNotFoundException extends Throwable {
    public ClientNotFoundException(String id) {
        super(String.format("Client with id [%s] could not be found.", id));
    }
}
