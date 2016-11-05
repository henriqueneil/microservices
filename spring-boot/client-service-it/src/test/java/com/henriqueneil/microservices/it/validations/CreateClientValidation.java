package com.henriqueneil.microservices.it.validations;

import com.consol.citrus.context.TestContext;
import com.consol.citrus.exceptions.ValidationException;
import com.consol.citrus.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Validation class for client service responses for create client operation
 */
public class CreateClientValidation extends DefaultClientValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateClientValidation.class);

    @Override
    public void validateMessage(Message response, Message expected, TestContext testContext, List list) throws ValidationException {
        super.validateMessage(response, expected, testContext, list);
        LOGGER.info("Message has been validated by default validation.");
    }

    public boolean supportsMessageType(String s, Message message) {
        return false;
    }
}
