package com.henriqueneil.microservices.it.validations;

import com.consol.citrus.context.TestContext;
import com.consol.citrus.exceptions.ValidationException;
import com.consol.citrus.message.Message;

import java.util.List;

/**
 * Validation class for update client operation
 */
public class UpdateClientValidation extends DefaultClientValidation {

    @Override
    public void validateMessage(Message response, Message expected, TestContext testContext, List list) throws ValidationException {
        super.validateMessage(response, expected, testContext, list);

        String responseId = getResponseId();
        String expectedId = getExpectedId();
        assertFieldEquals(responseId, expectedId, MESSAGE_VALIDATION_CLIENT_ID);
    }

    public boolean supportsMessageType(String s, Message message) {
        return false;
    }
}
