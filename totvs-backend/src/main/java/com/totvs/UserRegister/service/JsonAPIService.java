package com.totvs.UserRegister.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

public class JsonAPIService {

    protected static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    protected final WebClient webClient;

    @Autowired
    public JsonAPIService() {
        this.webClient = WebClient.create("http://localhost:3000");
    }

    public JsonAPIService(WebClient webClient) {
        this.webClient = webClient;
    }
}
