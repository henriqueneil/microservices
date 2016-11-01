package com.henriqueneil.microservices.springboot.client.model.dto;

import com.henriqueneil.microservices.springboot.client.model.enums.State;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * The class {@link Address} is an implementation of Data Transfer Object Patter
 */
@ApiModel(value = "Address", description = "Object that holds the information about the address.")
public class Address implements Serializable {

    @ApiModelProperty(name = "address", value = "The address can be street or avenue name, etc.")
    private String address;
    @ApiModelProperty(name = "suburb", value = "The address suburb.")
    private String suburb;
    @ApiModelProperty(name = "state", value = "The address state",
            allowableValues = "NSW, QLD, SA, TAS, VIC, WA")
    private State state;
    @ApiModelProperty(name = "number", value = "The address number.")
    private Integer number;
    @ApiModelProperty(name = "unit", value = "The address unit, if there is any.")
    private Integer unit;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }
}
