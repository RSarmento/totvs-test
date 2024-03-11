package com.totvs.UserRegister.controller;

import com.totvs.UserRegister.domain.PhoneNumber;
import com.totvs.UserRegister.exception.UserRegisterException;
import com.totvs.UserRegister.exception.ValidationException;
import com.totvs.UserRegister.service.PhoneNumberService;
import com.totvs.UserRegister.validation.UserRegisterValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * The type Phone number controller, contains the validation relating to the phone number
 */
@RestController
@RequestMapping("/validate")
public class PhoneNumberController {

    @Autowired
    private UserRegisterValidation userRegisterValidation;
    @Autowired
    private PhoneNumberService phoneNumberRegisterService;

    public PhoneNumberController() {
    }


    /**
     * Validate unique phone number response entity.
     *
     * @param phoneNumber the phone number
     * @return the response entity
     * @throws UserRegisterException the user register exception
     */
    @PostMapping
    public ResponseEntity<Boolean> validateUniquePhoneNumber(@RequestBody PhoneNumber phoneNumber)
            throws UserRegisterException {

        try {
            List<PhoneNumber> allPhoneNumberList = phoneNumberRegisterService.getAll();
            userRegisterValidation.phoneNumberIsUnique(allPhoneNumberList, phoneNumber);
        } catch (ValidationException e) {
            return ResponseEntity.ok().body(false);
        }
        return ResponseEntity.ok().body(true);
    }
}
