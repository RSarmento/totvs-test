package com.totvs.UserRegister.controller;

import com.totvs.UserRegister.domain.PhoneNumber;
import com.totvs.UserRegister.domain.UserWithID;
import com.totvs.UserRegister.dto.UserRegisterControllerRequestDto;
import com.totvs.UserRegister.exception.UserRegisterException;
import com.totvs.UserRegister.domain.User;
import com.totvs.UserRegister.exception.ValidationException;
import com.totvs.UserRegister.service.PhoneNumberService;
import com.totvs.UserRegister.service.UserRegisterService;
import com.totvs.UserRegister.validation.UserRegisterValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type User register controller
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserRegisterController {


    @Autowired
    private UserRegisterService userRegisterService;
    @Autowired
    private PhoneNumberService phoneNumberRegisterService;
    private UserRegisterValidation userRegisterValidation;

    /**
     * Instantiates a new User register controller.
     */
    public UserRegisterController() {
    }

    /**
     * Instantiates a new User register controller.
     *
     * @param userRegisterValidation the user register validation
     */
    @Autowired
    public UserRegisterController(UserRegisterValidation userRegisterValidation) {

        this.userRegisterValidation = userRegisterValidation;
    }

    /**
     * Create user response entity.
     *
     * @param newUserDto the new user dto
     * @return the response entity
     * @throws UserRegisterException the user register exception
     */
    @PostMapping
    public ResponseEntity<UserWithID> createUser(@RequestBody UserRegisterControllerRequestDto newUserDto)
            throws UserRegisterException {

        try {
            userRegisterValidation.validateUser(newUserDto);
            userRegisterValidation.validatePhoneNumber(newUserDto);
            UserWithID user = userRegisterService.createUser(mapUserFromDto(newUserDto));
            for (PhoneNumber newPhoneNumber : mapPhoneNumberListFromDto(newUserDto)){
                phoneNumberRegisterService.createPhoneNumber(newPhoneNumber, user.id());
            }
            return ResponseEntity.ok().body(user);
        } catch (ValidationException e){
            throw new UserRegisterException(e.getMessage(), e.getCause());
        }
    }

    private User mapUserFromDto(UserRegisterControllerRequestDto dto){

        return new User( dto.name(), dto.address(), dto.neighborhood());
    }

    private List<PhoneNumber> mapPhoneNumberListFromDto(UserRegisterControllerRequestDto dto){

        List<PhoneNumber> phoneNumberList = new ArrayList<>();
        for (String phoneNumber : dto.phoneNumberList()){
            phoneNumberList.add(new PhoneNumber(phoneNumber));
        }
        return phoneNumberList;
    }
}
