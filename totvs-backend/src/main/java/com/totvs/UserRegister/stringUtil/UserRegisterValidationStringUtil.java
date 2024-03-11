package com.totvs.UserRegister.stringUtil;

public class UserRegisterValidationStringUtil {

    public final static String USER_DATA_IS_NULL = "User data is missing";
    public final static String USER_NAME_IS_NULL = "User name is missing";
    public final static String USER_ADDRESS_IS_NULL = "User address is missing";
    public final static String USER_NEIGHBORHOOD_IS_NULL = "User neighborhood is missing";
    public final static String PHONE_NUMBER_IS_NULL = "Phone number is missing";
    public final static String PHONE_NUMBER_WRONG_LENGTH = "Phone number should be 1 digits long";
    public final static String INVALID_PHONE_NUMBER_FORMAT = "invalid phone number format";
    public static final String VALID_NUMBER_FORMAT = "^[0-9]{11}$";
    public static final String PHONE_NUMBER_ALREADY_REGISTERED = "Phone number already registered in database";
}
