package com.totvs.UserRegister.service;

import com.totvs.UserRegister.domain.User;
import com.totvs.UserRegister.domain.UserWithID;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserRegisterService extends JsonAPIService{


    public User getUser(long id) {

        return webClient
                .get()
                .uri("/users/" + id)
                .retrieve()
                .bodyToMono(User.class)
                .block(REQUEST_TIMEOUT);
    }

    public List<User> getAll() {

        return webClient
                .get()
                .uri("/user/")
                .retrieve()
                .bodyToFlux(User.class)
                .collectList()
                .block(REQUEST_TIMEOUT);
    }

    public UserWithID createUser(User newUser) {

        return webClient
                .post()
                .uri("/user/")
                .body(Mono.just(newUser), User.class)
                .retrieve()
                .bodyToMono(UserWithID.class)
                .block(REQUEST_TIMEOUT);
    }


}
