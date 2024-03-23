package com.app.RentWheelz.config;

import lombok.Getter;

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
}
