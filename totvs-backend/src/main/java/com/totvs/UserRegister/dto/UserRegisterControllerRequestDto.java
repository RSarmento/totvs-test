package com.totvs.UserRegister.dto;

import java.util.List;

public record UserRegisterControllerRequestDto(String name,
                                               String address,
                                               String neighborhood,
                                               List<String> phoneNumberList) {


}
