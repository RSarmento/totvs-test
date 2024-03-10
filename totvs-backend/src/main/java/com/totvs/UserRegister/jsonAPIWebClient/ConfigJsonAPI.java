package com.totvs.UserRegister.jsonAPIWebClient;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

public class ConfigJsonAPI {

    @Bean
    public WebClient localApiClient() {
        return WebClient.create("http://localhost:3000/");
    }
}
