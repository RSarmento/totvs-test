package com.totvs.UserRegister.service;

import com.totvs.UserRegister.domain.PhoneNumber;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PhoneNumberService extends JsonAPIService {

    public List<PhoneNumber> getAll(){
        return webClient
                .get()
                .uri("/phoneNumber/")
                .retrieve()
                .bodyToFlux(PhoneNumber.class)
                .collectList()
                .block(REQUEST_TIMEOUT);
    }

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
