package com.totvs.UserRegister.dto;



public record PhoneValidationResponseDto(String property, String pattern, String errorMessage) {

    /*fields: [{
  //             property: 'phone',
  //             pattern: '/^(?!.*).*$/',
  //             errorMessage: 'Telefone já cadastrado',
  //           }],
  //           focus: 'phone'

 */
}
