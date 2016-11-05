package com.henriqueneil.microservices.it.validations;

import com.consol.citrus.context.TestContext;
import com.consol.citrus.exceptions.ValidationException;
import com.consol.citrus.message.Message;
import com.consol.citrus.validation.MessageValidator;
import com.henriqueneil.microservices.it.utils.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static com.henriqueneil.microservices.it.utils.JsonUtils.*;

/**
 * Default validation class that contains basic validations for Client Entity
 */
public abstract class DefaultClientValidation implements MessageValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultClientValidation.class);

    private static final String UUID_REGEX = "[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}";
    private static final String MESSAGE_VALIDATION_UUID = "The returned id [%s] does not match the expected UUID format.";
    private static final String MESSAGE_VALIDATION_CLIENT_NAME = "Client name in response [%s] does not match the expected [%s].";
    private static final String MESSAGE_VALIDATION_CLIENT_EMAIL = "Client e-mail in response [%s] does not match the expected [%s].";
    protected static final String MESSAGE_VALIDATION_CLIENT_ID =
            "The client id [%s] in response does not match the expected [%s].";

    private Map<String, Object> responseMap;
    private Map<String, Object> expectedMap;

    public void validateMessage(Message response, Message expected, TestContext testContext, List list) throws ValidationException {

        responseMap = JsonUtils.jsonToMap(response.getPayload());
        expectedMap = JsonUtils.jsonToMap(expected.getPayload());

        String responseId = getPropertyAsString(responseMap, FIELD_ID);
        String responseName = getPropertyAsString(responseMap, FIELD_NAME);
        String responseEmail = getPropertyAsString(responseMap, FIELD_EMAIL);

        String expectedName = getPropertyAsString(expectedMap, FIELD_NAME);
        String expectedEmail = getPropertyAsString(expectedMap, FIELD_EMAIL);

        if (StringUtils.isNotBlank(responseId) && !responseId.matches(UUID_REGEX)) {
            LOGGER.info(String.format(MESSAGE_VALIDATION_UUID, responseId));
            throw new ValidationException(String.format(MESSAGE_VALIDATION_UUID, responseId));
        }

        assertFieldEquals(responseName, expectedName, MESSAGE_VALIDATION_CLIENT_NAME);
        assertFieldEquals(responseEmail, expectedEmail, MESSAGE_VALIDATION_CLIENT_EMAIL);
    }

    protected void assertFieldEquals(String value, String expected, String errorMessage) throws ValidationException {
        if (!expected.equalsIgnoreCase(value)) {
            LOGGER.info(String.format(errorMessage, value, expected));
            throw new ValidationException(String.format(errorMessage, value, expected));
        }
    }

    private String getPropertyAsString(Map<String, Object> map, String key) {
        Object object = map.get(key);
        return (object != null) ? object.toString() : null;
    }

    protected String getResponseId() {
        return getPropertyAsString(responseMap, FIELD_ID);
    }

    protected String getExpectedId() {
        return getPropertyAsString(expectedMap, FIELD_ID);
    }
}
