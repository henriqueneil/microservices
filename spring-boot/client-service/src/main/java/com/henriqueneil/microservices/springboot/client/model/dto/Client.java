package com.henriqueneil.microservices.springboot.client.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The class {@link Client} is a implementation of Data Transfer Object Pattern
 */
@ApiModel(value = "Client",
        description = "Object that holds client information")
@Entity(name = "client")
@Table(name = "CLIENT")
public class Client implements Serializable {

    @ApiModelProperty(name = "id", value = "The client id.")
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;
    @ApiModelProperty(name = "name", value = "The client name.")
    @Column(name = "name")
    private String name;
    @ApiModelProperty(name = "email", value = "The client email.")
    @Column(name = "email")
    private String email;
    @ApiModelProperty(name = "Address", value = "The client address.")
    @Transient
    private Address address;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
}
