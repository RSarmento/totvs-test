package com.totvs.UserRegister.service;

import com.totvs.UserRegister.domain.PhoneNumber;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * The type Phone number service. Used to access Json API Server.
 */
@Service
public class PhoneNumberService extends JsonAPIService {

    /**
     * Get all list.
     *
     * @return the list
     */
    public List<PhoneNumber> getAll(){
        return webClient
                .get()
                .uri("/phoneNumber/")
                .retrieve()
                .bodyToFlux(PhoneNumber.class)
                .collectList()
                .block(REQUEST_TIMEOUT);
    }

    /**
     * Create phone number associated with a user.
     *
     * @param newPhoneNumber the new phone number
     * @param userID         the user id
     */
    public void createPhoneNumber(PhoneNumber newPhoneNumber, String userID) {
        newPhoneNumber.setUserID(userID);
        webClient
                .post()
                .uri("/phoneNumber")
                .body(Mono.just(newPhoneNumber), PhoneNumber.class)
                .retrieve()
                .bodyToMono(PhoneNumber.class)
                .block(REQUEST_TIMEOUT);
    }
}
