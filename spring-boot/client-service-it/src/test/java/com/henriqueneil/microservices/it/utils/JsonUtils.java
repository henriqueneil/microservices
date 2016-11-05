package com.henriqueneil.microservices.it.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Json util class
 */
public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_EMAIL = "email";

    /**
     * Method that collects all the fields in the client response and put in a map.
     * @param json The message to be parsed.
     * @return Map The object containing the fields of the message in a Map.
     */
    public static Map<String, Object> jsonToMap(Object json) {

        Map<String, Object> map = new TreeMap<String, Object>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readValue(json.toString(), JsonNode.class);

            String responseId = jsonNode.get(FIELD_ID).asText();
            String responseName = jsonNode.get(FIELD_NAME).asText();
            String responseEmail = jsonNode.get(FIELD_EMAIL).asText();

            if (StringUtils.isNotBlank(responseId)) {
                map.put(FIELD_ID, responseId);
            }
            map.put(FIELD_NAME, responseName);
            map.put(FIELD_EMAIL, responseEmail);
        } catch (IOException ioException) {
            LOGGER.error("An exception happened while trying to parse the Json object to Map.", ioException);
        }
        return map;
    }
}
