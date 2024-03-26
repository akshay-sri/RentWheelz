package com.app.RentWheelz.services;

import com.app.RentWheelz.exceptions.BadRequestException;
import com.app.RentWheelz.models.User;
import com.app.RentWheelz.payloads.LoginRequest;
import com.app.RentWheelz.payloads.ApiResponse;
import com.app.RentWheelz.payloads.RegisterRequest;
import com.app.RentWheelz.repositories.UserRepository;
import com.app.RentWheelz.utils.UserHelper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.app.RentWheelz.config.Constants.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public ApiResponse registration(RegisterRequest userDto) {
        logger.info("Inside UserService.registration");
        try {
            List<String> validations = UserHelper.validateRegisterUser(userDto);
            if (validations.isEmpty()) {
                if (userRepository.findByUserName(userDto.getUserName()) == NULL_CHECK && userRepository.findByEmail(userDto.getEmail()) == NULL_CHECK && userRepository.findByMobileNumber(userDto.getMobileNumber()) == NULL_CHECK) {
                    User user = this.modelMapper.map(userDto, User.class);
                    user.setUserName(userDto.getUserName());
                    user.setEmail(userDto.getEmail());
                    user.setGender(userDto.getGender());
                    user.setPassword(userDto.getPassword());
                    user.setProofId(userDto.getProofId());
                    user.setFirstName(userDto.getFirstName());
                    user.setLastName(userDto.getLastName());
                    user.setMobileNumber(userDto.getMobileNumber());
                    User savedUser = this.userRepository.save(user);
                    logger.info("Exit from UserService.registration:{}", savedUser);
                    return new ApiResponse(SUCCESS_STATUS, SUCCESSFUL_REGISTER_MESSAGE, EMPTY_LIST);
                } else {
                    User u1 = this.userRepository.findByUserName(userDto.getUserName());
                    User u2 = this.userRepository.findByEmail(userDto.getEmail());
                    User u3 = this.userRepository.findByMobileNumber(userDto.getMobileNumber());
                    if (u1 != NULL_CHECK && u2 != NULL_CHECK && u3 != NULL_CHECK) {
                        logger.error(USER_WITH_USERNAME + u1.getUserName() + AND_EMAIL + u2.getEmail() + ALREADY_EXISTS);
                        throw new BadRequestException(USER_WITH_USERNAME + u1.getUserName() + AND_EMAIL + u2.getEmail() + ALREADY_EXISTS+AND_MOBILE_NUMBER+u3.getMobileNumber()+ALREADY_EXISTS);
                    } else if (u1 != NULL_CHECK) {
                        logger.error(USER_WITH_USERNAME + u1.getUserName() + ALREADY_EXISTS);
                        throw new BadRequestException(USER_WITH_USERNAME + u1.getUserName() + ALREADY_EXISTS);
                    } else if (u2 != NULL_CHECK) {
                        logger.error(USER_WITH_EMAIL + u2.getEmail() + ALREADY_EXISTS);
                        throw new BadRequestException(USER_WITH_EMAIL + u2.getEmail() + ALREADY_EXISTS);
                    } else if (u3 != NULL_CHECK) {
                        logger.error(USER_WITH_MOBILE_NUMBER + u3.getMobileNumber() + ALREADY_EXISTS);
                        throw new BadRequestException(USER_WITH_MOBILE_NUMBER + u3.getMobileNumber() + ALREADY_EXISTS);
                    }
                }
            }
            ApiResponse response = new ApiResponse(FAILURE_STATUS, VIOLATED_FIELD_MESSAGE, validations);
            logger.info("Exit from UserService.registration:{}", response);
            return response;
        } catch (NullPointerException ex) {
            logger.error(NULL_REQUEST);
            throw new BadRequestException(NULL_REQUEST);
        }
    }

    public ApiResponse login(LoginRequest request) {
        logger.info("Inside UserService.login");
        try {
            List<String> validations = UserHelper.validateLoginUser(request);
            if (validations.isEmpty()) {
                User user = this.userRepository.findByUserName(request.getUserName());
                if (user != NULL_CHECK) {
                    if (user.getPassword().equals(request.getPassword())) {
                        logger.info("Exit from UserService.login:{}", user);
                        return new ApiResponse(SUCCESS_STATUS, SUCCESSFUL_LOGIN_MESSAGE, EMPTY_LIST);
                    } else {
                        logger.error(INCORRECT_PASSWORD);
                        throw new BadRequestException(INCORRECT_PASSWORD);
                    }
                } else {
                    logger.error(USER_WITH_USERNAME + request.getUserName() + NOT_FOUND);
                    throw new BadRequestException(USER_WITH_USERNAME + request.getUserName() + NOT_FOUND);
                }
            } else {
                ApiResponse response = new ApiResponse(FAILURE_STATUS, VIOLATED_FIELD_MESSAGE, validations);
                logger.info("Exit from UserService.login:{}", response);
                return response;
            }
        } catch (NullPointerException e) {
            logger.error(NULL_REQUEST);
            throw new BadRequestException(NULL_REQUEST);
        }
    }

    public RegisterRequest myProfile(String userName) {
        logger.info("Inside UserService.myProfile");
        try {
            User user = this.userRepository.findByUserName(userName);
            RegisterRequest response = this.modelMapper.map(user, RegisterRequest.class);
            response.setEmail(user.getEmail());
            response.setPassword(user.getPassword());
            response.setGender(user.getGender());
            response.setUserName(user.getUserName());
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setMobileNumber(user.getMobileNumber());
            response.setProofId(user.getProofId());
            logger.info("Exit from UserService.myProfile:{}", response);
            return response;
        } catch (IllegalArgumentException e) {
            logger.error(USER_WITH_USERNAME + userName + NOT_FOUND);
            throw new BadRequestException(USER_WITH_USERNAME + userName + NOT_FOUND);
        }
    }
}
