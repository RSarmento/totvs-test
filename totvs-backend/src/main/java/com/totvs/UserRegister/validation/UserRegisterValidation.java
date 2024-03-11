package com.totvs.UserRegister.validation;

import com.totvs.UserRegister.domain.PhoneNumber;
import com.totvs.UserRegister.domain.User;
import com.totvs.UserRegister.dto.UserRegisterControllerRequestDto;
import com.totvs.UserRegister.exception.ValidationException;
import com.totvs.UserRegister.stringUtil.UserRegisterValidationStringUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRegisterValidation {

    public UserRegisterValidation() {
    }

    public void validateUser(UserRegisterControllerRequestDto dto)
            throws ValidationException {

        if (dto == null) {
            throw new ValidationException(UserRegisterValidationStringUtil.USER_DATA_IS_NULL);
        }
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new ValidationException(UserRegisterValidationStringUtil.USER_NAME_IS_NULL);
        }
        if (dto.getAddress() == null || dto.getAddress().isBlank()) {
            throw new ValidationException(UserRegisterValidationStringUtil.USER_ADDRESS_IS_NULL);
        }
        if (dto.getNeighborhood() == null || dto.getNeighborhood().isBlank()) {
            throw new ValidationException(UserRegisterValidationStringUtil.USER_NEIGHBORHOOD_IS_NULL);
        }
    }

    public void validatePhoneNumber(UserRegisterControllerRequestDto newUserDto)
            throws ValidationException {

        if (newUserDto.getPhoneNumberList() == null || newUserDto.getPhoneNumberList().isEmpty()) {
            throw new ValidationException(UserRegisterValidationStringUtil.PHONE_NUMBER_IS_NULL);
        }
        for (String phoneNumber : newUserDto.getPhoneNumberList()) {
            if (phoneNumber == null || phoneNumber.isBlank()) {
                throw new ValidationException(UserRegisterValidationStringUtil.PHONE_NUMBER_IS_NULL);
            } else if (!phoneNumber.matches(UserRegisterValidationStringUtil.VALID_NUMBER_FORMAT)) {
                throw new ValidationException(UserRegisterValidationStringUtil.INVALID_PHONE_NUMBER_FORMAT);
            }
        }
    }

    public void phoneNumberIsUnique(List<PhoneNumber> allPhoneNumberList, PhoneNumber phoneNumberToValidate)
            throws ValidationException {

        if (phoneNumberToValidate == null ||
                phoneNumberToValidate.getNumber() == null ||
                phoneNumberToValidate.getNumber().isBlank()) {
            throw new ValidationException(UserRegisterValidationStringUtil.PHONE_NUMBER_IS_NULL);
        }
        if (phoneNumberToValidate.getNumber().length() != 11){
            throw new ValidationException(UserRegisterValidationStringUtil.PHONE_NUMBER_WRONG_LENGTH);
        }
        for (PhoneNumber existentPhoneNumber : allPhoneNumberList) {
            if (existentPhoneNumber.getNumber().equals(phoneNumberToValidate.getNumber())) {
                throw new ValidationException(UserRegisterValidationStringUtil.PHONE_NUMBER_ALREADY_REGISTERED);
            }
        }
    }
}
