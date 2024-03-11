package com.totvs.UserRegister.validation;

import com.totvs.UserRegister.domain.PhoneNumber;
import com.totvs.UserRegister.dto.UserRegisterControllerRequestDto;
import com.totvs.UserRegister.exception.ValidationException;
import com.totvs.UserRegister.stringUtil.UserRegisterErrorMessages;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterValidationTest {

    private Exception exception;
    private static UserRegisterValidation userRegisterValidation;
    private static final String VALID_USER_NAME = "Valid Username";
    private static final String VALID_USER_ADDRESS = "Valid address";
    private static final String VALID_USER_NEIGHBORHOOD = "Valid neighborhood";
    private static final List<String> invalidStringValueList = new ArrayList<>(Arrays.asList(null, "", " "));
    private static final List<String> validPhoneNumberList = new ArrayList<>(Arrays.asList("00000000000", "11111111111"));


    @BeforeAll
    public static void setUp() {

        userRegisterValidation = new UserRegisterValidation();
    }

    @Test
    void validateUser_Success() {

        assertDoesNotThrow(() -> userRegisterValidation
                .validateUser(new UserRegisterControllerRequestDto(VALID_USER_NAME,
                        VALID_USER_ADDRESS,
                        VALID_USER_NEIGHBORHOOD,
                        validPhoneNumberList)));
    }

    @Test
    void validateUser_Fail_UserDataIsMissing() {

        exception = assertThrows(
                ValidationException.class,
                () -> userRegisterValidation
                        .validateUser(null));
        assertEquals(UserRegisterErrorMessages.USER_DATA_IS_MISSING, exception.getMessage());
    }

    @Test
    void validateUser_Fail_UserNameIsMissing() {

        for (String testStringValue : invalidStringValueList) {
            exception = assertThrows(
                    ValidationException.class,
                    () -> userRegisterValidation
                            .validateUser(new UserRegisterControllerRequestDto(testStringValue,
                                    VALID_USER_ADDRESS,
                                    VALID_USER_NEIGHBORHOOD,
                                    validPhoneNumberList)));
            assertEquals(UserRegisterErrorMessages.USER_NAME_IS_MISSING, exception.getMessage());
        }
    }

    @Test
    void validateUser_Fail_UserAddressIsMissing() {

        for (String testStringValue : invalidStringValueList) {
            exception = assertThrows(
                    ValidationException.class,
                    () -> userRegisterValidation
                            .validateUser(new UserRegisterControllerRequestDto(VALID_USER_NAME,
                                    testStringValue,
                                    VALID_USER_NEIGHBORHOOD,
                                    validPhoneNumberList)));
            assertEquals(UserRegisterErrorMessages.USER_ADDRESS_IS_MISSING, exception.getMessage());
        }
    }

    @Test
    void validateUser_Fail_UserNeighborhoodIsMissing() {

        for (String testStringValue : invalidStringValueList) {
            exception = assertThrows(
                    ValidationException.class,
                    () -> userRegisterValidation
                            .validateUser(new UserRegisterControllerRequestDto(VALID_USER_NAME,
                                    VALID_USER_ADDRESS,
                                    testStringValue,
                                    validPhoneNumberList)));
            assertEquals(UserRegisterErrorMessages.USER_NEIGHBORHOOD_IS_MISSING, exception.getMessage());
        }
    }

    @Test
    void validatePhoneNumber_Success() {

        assertDoesNotThrow(() -> userRegisterValidation
                .validateUser(new UserRegisterControllerRequestDto(VALID_USER_NAME,
                        VALID_USER_ADDRESS,
                        VALID_USER_NEIGHBORHOOD,
                        validPhoneNumberList)));
    }

    @Test
    void validatePhoneNumber_Fail_PhoneNumberListMissing() {

        List<List<String>> invalidLists = new ArrayList<>(Arrays.asList(null,
                new ArrayList<>(),
                new ArrayList<>(Collections.singletonList(null)),
                new ArrayList<>(Collections.singletonList("")),
                new ArrayList<>(Collections.singletonList(" "))));
        for (List<String> invalidList : invalidLists) {
            exception = assertThrows(
                    ValidationException.class,
                    () -> userRegisterValidation
                            .validatePhoneNumber(new UserRegisterControllerRequestDto(VALID_USER_NAME,
                                    VALID_USER_ADDRESS,
                                    VALID_USER_NEIGHBORHOOD,
                                    invalidList)));
            assertEquals(UserRegisterErrorMessages.PHONE_NUMBER_IS_MISSING, exception.getMessage());
        }
    }

    @Test
    void validatePhoneNumber_Fail_InvalidPhoneNumberLength() {

        exception = assertThrows(ValidationException.class,
                () -> userRegisterValidation
                        .validatePhoneNumber(new UserRegisterControllerRequestDto(VALID_USER_NAME,
                                VALID_USER_ADDRESS,
                                VALID_USER_NEIGHBORHOOD,
                                new ArrayList<>(Collections.singletonList("1")))));
        assertEquals(UserRegisterErrorMessages.INVALID_PHONE_NUMBER_LENGTH, exception.getMessage());
    }

    @Test
    void validatePhoneNumberIsUnique_Success() {

        String validUniquePhoneNumber = "22222222222";
        assertDoesNotThrow(() -> userRegisterValidation
                .phoneNumberIsUnique(validPhoneNumberList.stream()
                                .map(PhoneNumber::new)
                                .collect(Collectors.toList()),
                        new PhoneNumber(validUniquePhoneNumber)));
    }

    @Test
    void validatePhoneNumberIsUnique_Fail_PhoneNumberAlreadyRegistered() {

        for (String validPhoneNumber : validPhoneNumberList) {
            exception = assertThrows(ValidationException.class,
                    () -> userRegisterValidation
                            .phoneNumberIsUnique(validPhoneNumberList.stream()
                                            .map(PhoneNumber::new)
                                            .collect(Collectors.toList()),
                                    new PhoneNumber(validPhoneNumber)));
            assertEquals(UserRegisterErrorMessages.PHONE_NUMBER_ALREADY_REGISTERED, exception.getMessage());
        }

    }

    @Test
    void validatePhoneNumberIsUnique_Fail_PhoneNumberIsMissing() {

        exception = assertThrows(ValidationException.class,
                () -> userRegisterValidation
                        .phoneNumberIsUnique(validPhoneNumberList.stream()
                                        .map(PhoneNumber::new)
                                        .collect(Collectors.toList()),
                                null));
        assertEquals(UserRegisterErrorMessages.PHONE_NUMBER_IS_MISSING, exception.getMessage());
    }
}
