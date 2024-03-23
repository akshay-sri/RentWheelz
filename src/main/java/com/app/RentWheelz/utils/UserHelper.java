package com.app.RentWheelz.utils;

import com.app.RentWheelz.config.Constants;
import com.app.RentWheelz.payloads.UserDto;
import com.app.RentWheelz.services.UserService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Getter
@Setter
public class UserHelper {

    private UserHelper() {
    }

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public static List<String> validateUser(UserDto user) {
        logger.info("Inside UserHelper.validateUser");
        List<String> validationErrors = new ArrayList<>();
        validateFieldNotEmpty(user.getUserName(), "userName", validationErrors);
        validatePatternMatches(user.getEmail(), "email", validationErrors);
        validatePatternMatches(user.getPassword(), "password", validationErrors);
        validateFieldNotEmpty(user.getProofId(), "proofId", validationErrors);
        validatePatternMatches(user.getGender().toString(), "gender", validationErrors);
        validatePatternMatches(user.getMobileNumber(), "mobileNumber", validationErrors);
        validateFieldNotEmpty(user.getFirstName(), "firstName", validationErrors);
        validateFieldNotEmpty(user.getLastName(), "lastName", validationErrors);
        if (!validationErrors.isEmpty()) {
            logger.error("Validation errors for User: {}", validationErrors);
        }
        logger.info("Exit from UserHelper.validateUser");
        return validationErrors;
    }

    private static void validateFieldNotEmpty(String fieldValue, String fieldName, List<String> errors) {
        if (fieldValue == null || fieldValue.isEmpty()) {
            errors.add(fieldName + " is required");
        }
    }

    private static void validatePatternMatches(String fieldValue, String fieldName, List<String> errors) {
        if (fieldValue == null || fieldValue.isEmpty()) {
            errors.add(fieldName + "is required");
        } else {
            if (fieldName == "email") {
                if (!Pattern.matches(Constants.EMAIL_REGEX, fieldValue)) {
                    errors.add(fieldName + " should match the pattern");
                }
            } else if (fieldName == "password") {
                if (!Pattern.matches(Constants.PASSWORD_REGEX, fieldValue)) {
                    errors.add(fieldName + " should match the pattern");
                }
            } else if (fieldName=="gender") {
                if(!Pattern.matches(Constants.GENDER_REGEX,fieldValue)){
                    errors.add(fieldName+" should match the pattern");
                }
            } else {
                if (Pattern.matches(Constants.MOBILE_NUMBER_REGEX, fieldValue)) {
                    errors.add(fieldName + " should match the pattern");
                }
            }
        }
    }
}
