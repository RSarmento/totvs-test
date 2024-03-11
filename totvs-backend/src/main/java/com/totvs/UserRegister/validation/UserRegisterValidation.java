package com.totvs.UserRegister.validation;

import com.totvs.UserRegister.domain.PhoneNumber;
import com.totvs.UserRegister.dto.UserRegisterControllerRequestDto;
import com.totvs.UserRegister.exception.ValidationException;
import com.totvs.UserRegister.stringUtil.UserRegisterErrorMessages;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserRegisterValidation {

    public UserRegisterValidation() {
    }

    public void validateUser(UserRegisterControllerRequestDto dto)
            throws ValidationException {

        if (dto == null) {
            throw new ValidationException(UserRegisterErrorMessages.USER_DATA_IS_MISSING);
        }
        if (dto.name() == null || dto.name().isBlank()) {
            throw new ValidationException(UserRegisterErrorMessages.USER_NAME_IS_MISSING);
        }
        if (dto.address() == null || dto.address().isBlank()) {
            throw new ValidationException(UserRegisterErrorMessages.USER_ADDRESS_IS_MISSING);
        }
        if (dto.neighborhood() == null || dto.neighborhood().isBlank()) {
            throw new ValidationException(UserRegisterErrorMessages.USER_NEIGHBORHOOD_IS_MISSING);
        }
    }

    public void validatePhoneNumber(UserRegisterControllerRequestDto newUserDto)
            throws ValidationException {

        if (newUserDto.phoneNumberList() == null || newUserDto.phoneNumberList().isEmpty()) {
            throw new ValidationException(UserRegisterErrorMessages.PHONE_NUMBER_IS_MISSING);
        }
        for (String phoneNumber : newUserDto.phoneNumberList()) {
            if (phoneNumber == null || phoneNumber.isBlank()) {
                throw new ValidationException(UserRegisterErrorMessages.PHONE_NUMBER_IS_MISSING);
            } else if (!phoneNumber.matches(UserRegisterErrorMessages.VALID_NUMBER_FORMAT)) {
                throw new ValidationException(UserRegisterErrorMessages.INVALID_PHONE_NUMBER_LENGTH);
            }
        }
    }

    public void phoneNumberIsUnique(List<PhoneNumber> allPhoneNumberList, PhoneNumber phoneNumberToValidate)
            throws ValidationException {

        validatePhoneNumber(new UserRegisterControllerRequestDto(null,
                null,
                null,
                phoneNumberToValidate == null ?
                        null :
                        Collections.singletonList(phoneNumberToValidate.getNumber())));
        for (PhoneNumber existentPhoneNumber : allPhoneNumberList) {
            assert phoneNumberToValidate != null;
            if (existentPhoneNumber.getNumber().equals(phoneNumberToValidate.getNumber())) {
                throw new ValidationException(UserRegisterErrorMessages.PHONE_NUMBER_ALREADY_REGISTERED);
            }
        }
    }
}
