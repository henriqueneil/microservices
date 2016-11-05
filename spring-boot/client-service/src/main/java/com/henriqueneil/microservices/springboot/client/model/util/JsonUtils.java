package com.henriqueneil.microservices.springboot.client.model.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Util class to work with JSON
 */
public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    public static String fromObjectToJson(Object object) {
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while trying to convert the object to JSON.", e);
        }
        return json;
    }
}
