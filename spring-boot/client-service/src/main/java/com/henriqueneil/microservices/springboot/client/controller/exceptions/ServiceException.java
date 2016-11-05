package com.henriqueneil.microservices.springboot.client.controller.exceptions;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * {@link ServiceException} is the class that holds all the technical exceptions for the services.
 */
@JsonRootName("exception")
@JsonPropertyOrder({"type", "exceptionCode", "exceptionMessage"})
public class ServiceException implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceException.class);

    private String type;
    private String exceptionCode;
    private String exceptionMessage;

    public ServiceException(String type, String exceptionCode, String exceptionMessage) {
        this.type = type;
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String toString() {
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while trying to convert ServiceException to JSON.", e);
        }
        return json;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
