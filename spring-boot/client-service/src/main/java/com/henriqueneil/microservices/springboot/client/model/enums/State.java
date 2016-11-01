package com.henriqueneil.microservices.springboot.client.model.enums;

/**
 * The {@link State} holds the information about the state
 */
public enum State {

    NSW("NSW", "New South Wales"),
    QLD("QLD", "Queesland"),
    SA("SA", "South Australia"),
    TAS("TAS", "Tasmania"),
    VIC("VIC", "Victoria"),
    WA("WA", "Western Australia");

    private final String stateAbbr;
    private final String stateName;

    State(String stateAbbr, String stateName) {
        this.stateAbbr = stateAbbr;
        this.stateName = stateName;
    }
}
