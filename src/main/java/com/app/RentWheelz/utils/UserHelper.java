package com.app.RentWheelz.utils;

import com.app.RentWheelz.payloads.UserDto;
import com.app.RentWheelz.services.UserService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.app.RentWheelz.config.Constants.*;

@Getter
@Setter
public class UserHelper {

    private UserHelper() {
    }

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public static List<String> validateUser(UserDto user) {
        logger.info("Inside UserHelper.validateUser");
        List<String> validationErrors = new ArrayList<>();
        validateFieldNotEmpty(user.getUserName(), USERNAME, validationErrors);
        validatePatternMatches(user.getEmail(), EMAIL, validationErrors);
        validatePatternMatches(user.getPassword(), PASSWORD, validationErrors);
        validateFieldNotEmpty(user.getProofId(), PROOF_ID, validationErrors);
        validatePatternMatches(user.getGender().toString(), GENDER, validationErrors);
        validatePatternMatches(user.getMobileNumber(), MOBILE_NUMBER, validationErrors);
        validateFieldNotEmpty(user.getFirstName(), FIRSTNAME, validationErrors);
        validateFieldNotEmpty(user.getLastName(), LASTNAME, validationErrors);
        if (!validationErrors.isEmpty()) {
            logger.error("Validation errors for User: {}", validationErrors);
        }
        logger.info("Exit from UserHelper.validateUser");
        return validationErrors;
    }

    private static void validateFieldNotEmpty(String fieldValue, String fieldName, List<String> errors) {
        if (fieldValue == NULL_CHECK || fieldValue.isEmpty()) {
            errors.add(fieldName + IS_REQUIRED);
        }
    }

    private static void validatePatternMatches(String fieldValue, String fieldName, List<String> errors) {
        if (fieldValue == NULL_CHECK || fieldValue.isEmpty()) {
            errors.add(fieldName + IS_REQUIRED);
        } else {
            if (fieldName == EMAIL) {
                if (!Pattern.matches(EMAIL_REGEX, fieldValue)) {
                    errors.add(fieldName + PATTERN_MATCH_MESSAGE);
                }
            } else if (fieldName == PASSWORD) {
                if (!Pattern.matches(PASSWORD_REGEX, fieldValue)) {
                    errors.add(fieldName + PATTERN_MATCH_MESSAGE);
                }
            } else if (fieldName==GENDER) {
                if(!Pattern.matches(GENDER_REGEX,fieldValue)){
                    errors.add(fieldName+PATTERN_MATCH_MESSAGE);
                }
            } else {
                if (Pattern.matches(MOBILE_NUMBER_REGEX, fieldValue)) {
                    errors.add(fieldName + PATTERN_MATCH_MESSAGE);
                }
            }
        }
    }
}
