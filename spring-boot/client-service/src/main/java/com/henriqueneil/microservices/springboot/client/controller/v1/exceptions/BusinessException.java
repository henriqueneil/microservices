package com.henriqueneil.microservices.springboot.client.controller.v1.exceptions;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;

/**
 * {@link BusinessException} is a class that holds all the business exception
 */
@JsonRootName("exception")
@JsonPropertyOrder({"type", "exceptionCode", "exceptionMessage"})
public class BusinessException implements Serializable {

    private String exceptionCode;
    private String exceptionMessage;

    public BusinessException(String exceptionCode, String exceptionMessage) {
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
}
