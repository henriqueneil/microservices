package com.henriqueneil.microservices.it.config;

import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.http.client.HttpEndpointConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * Http Configuration for HTTP requests
 */
@Component
public class HttpConfig {

    @Bean(name = "httpCreateClient")
    public HttpClient httpCreateClient() {

        HttpEndpointConfiguration configuration = new HttpEndpointConfiguration();
        configuration.setRequestUrl("http://localhost:8080/client-services/V1/create");
        configuration.setCharset("UTF-8");
        configuration.setRequestMethod(HttpMethod.POST);
        configuration.setContentType("application/json");
        HttpClient httpClient = new HttpClient(configuration);
        return httpClient;
    }

    @Bean(name = "httpGetClient")
    public HttpClient httpGetClient() {

        HttpEndpointConfiguration configuration = new HttpEndpointConfiguration();
        configuration.setRequestUrl("http://localhost:8080/client-services/V1/get");
        configuration.setCharset("UTF-8");
        configuration.setRequestMethod(HttpMethod.POST);
        configuration.setContentType("application/json");
        HttpClient httpClient = new HttpClient(configuration);
        return httpClient;
    }

    @Bean(name = "httpUpdateClient")
    public HttpClient httpUpdateClient() {

        HttpEndpointConfiguration configuration = new HttpEndpointConfiguration();
        configuration.setRequestUrl("http://localhost:8080/client-services/V1/update");
        configuration.setCharset("UTF-8");
        configuration.setRequestMethod(HttpMethod.POST);
        configuration.setContentType("application/json");
        HttpClient httpClient = new HttpClient(configuration);
        return httpClient;
    }

    @Bean(name = "httpDeleteClient")
    public HttpClient httpDeleteClient() {

        HttpEndpointConfiguration configuration = new HttpEndpointConfiguration();
        configuration.setRequestUrl("http://localhost:8080/client-services/V1/delete");
        configuration.setCharset("UTF-8");
        configuration.setRequestMethod(HttpMethod.POST);
        configuration.setContentType("application/json");
        HttpClient httpClient = new HttpClient(configuration);
        return httpClient;
    }
}
