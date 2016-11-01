package com.henriqueneil.microservices.springboot.client.controller.v1.exceptions;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;

/**
 * {@link ServiceException} is the class that holds all the technical exceptions for the services.
 */
@JsonRootName("exception")
@JsonPropertyOrder({"type", "exceptionCode", "exceptionMessage"})
public class ServiceException implements Serializable {

    private String type;
    private String exceptionCode;
    private String exceptionMessage;

    public ServiceException(String type, String exceptionCode, String exceptionMessage) {
        this.type = type;
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
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
