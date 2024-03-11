package com.totvs.UserRegister.service;

import com.totvs.UserRegister.domain.User;
import com.totvs.UserRegister.domain.UserWithID;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * The type User register service. Used to access Json API Server.
 */
@Service
public class UserRegisterService extends JsonAPIService{

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    public User getUser(long id) {

        return webClient
                .get()
                .uri("/users/" + id)
                .retrieve()
                .bodyToMono(User.class)
                .block(REQUEST_TIMEOUT);
    }

    /**
     * Gets all users.
     *
     * @return the all
     */
    public List<User> getAll() {

        return webClient
                .get()
                .uri("/user/")
                .retrieve()
                .bodyToFlux(User.class)
                .collectList()
                .block(REQUEST_TIMEOUT);
    }

    /**
     * Creates a user.
     *
     * @param newUser the new user
     * @return the user with id
     */
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
