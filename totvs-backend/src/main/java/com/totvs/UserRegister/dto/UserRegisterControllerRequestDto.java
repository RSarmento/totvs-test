package com.totvs.UserRegister.dto;

import java.util.List;

/**
 * The type User register controller request dto. Used for the request object.
 */
public record UserRegisterControllerRequestDto(String name,
                                               String address,
                                               String neighborhood,
                                               List<String> phoneNumberList) {
}
