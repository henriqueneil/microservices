package com.henriqueneil.microservices.springboot.client.model.enums;

/**
 * Enum for string search criteria
 */
public enum StringSearchCriteria {
    STARTS_WITH("startswith"),
    ENDS_WITH("endswith"),
    CONTAINS("contains");

    private String value;

    StringSearchCriteria(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StringSearchCriteria getCriteria(String value) {
        if ("startswith".equalsIgnoreCase(value)) {
            return STARTS_WITH;
        } else if ("endswith".equalsIgnoreCase(value)) {
            return ENDS_WITH;
        } else if ("contains".equalsIgnoreCase(value)) {
            return CONTAINS;
        }
        return null;
    }
}
