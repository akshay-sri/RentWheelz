package com.app.RentWheelz.config;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class Constants {
    public static final String EMAIL_REGEX = ".+@.+\\.[a-z]+";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$";
    public static final String MOBILE_NUMBER_REGEX = "^[+]{1}(?:[0-9\\-\\(\\)\\/" +
            "\\.]\\s?){6,15}[0-9]{1}$";
    public static final String GENDER_REGEX = "(?:m|M|male|Male|f|F|female|Female|FEMALE|MALE|Not prefer to say)$";
    public static final String ALREADY_EXISTS = " already exists";
    public static final String NULL_REQUEST = "Request body cannot be null";
    public static final String SUCCESSFUL_REGISTER_MESSAGE = "User registered successfully";
    public static final String SUCCESS_STATUS = "Success!";
    public static final String USER_WITH_USERNAME = "User with userName: ";
    public static final String AND_EMAIL = " and email: ";
    public static final String USER_WITH_EMAIL = "User with email: ";
    public static final String FAILURE_STATUS = "Failure";
    public static final String VIOLATED_FIELD_MESSAGE = "There are some violated fields";
    public static final String USERNAME = "userName";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String PROOF_ID = "proofId";
    public static final String GENDER = "gender";
    public static final String MOBILE_NUMBER = "mobileNumber";
    public static final String FIRSTNAME = "firstName";
    public static final String LASTNAME = "lastName";
    public static final String IS_REQUIRED = " is required";
    public static final String PATTERN_MATCH_MESSAGE = " should match the pattern";
    public static final Object NULL_CHECK = null;
    public static final String BAD_REQUEST = "Bad Request";
    public static final String NOT_FOUND = " not found";
    public static final String SUCCESSFUL_LOGIN_MESSAGE = "User login successfully";
    public static final String INCORRECT_PASSWORD = "Entered password is incorrect";
    public static final List<String> EMPTY_LIST = Collections.emptyList();
    public static final String USER_WITH_MOBILE_NUMBER = "User with mobileNumber: ";
    public static final String AND_MOBILE_NUMBER = " and mobileNumber: ";
}
