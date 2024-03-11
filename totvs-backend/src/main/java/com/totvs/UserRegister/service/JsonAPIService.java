package com.totvs.UserRegister.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

/**
 * The type Json api service. Used to connect with Json API Server.
 */
public class JsonAPIService {

    /**
     * The constant REQUEST_TIMEOUT.
     */
    protected static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    /**
     * The Web client.
     */
    protected final WebClient webClient;

    /**
     * Instantiates a new Json api service connection.
     */
    @Autowired
    public JsonAPIService() {
        this.webClient = WebClient.create("http://localhost:3000");
    }

    /**
     * Instantiates a new Json api service.
     *
     * @param webClient the web client
     */
    public JsonAPIService(WebClient webClient) {
        this.webClient = webClient;
    }
}
