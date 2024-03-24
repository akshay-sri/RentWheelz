package com.app.RentWheelz.services;

import com.app.RentWheelz.exceptions.BadRequestException;
import com.app.RentWheelz.models.User;
import com.app.RentWheelz.payloads.ApiResponse;
import com.app.RentWheelz.payloads.UserDto;
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

    public ApiResponse registration(UserDto userDto) {
        logger.info("Inside UserService.registration");
        try {
            List<String> validations = UserHelper.validateUser(userDto);
            if (validations.isEmpty()) {
                if (userRepository.findByUserName(userDto.getUserName()) == NULL_CHECK && userRepository.findByEmail(userDto.getEmail()) == NULL_CHECK) {
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
                    return new ApiResponse(SUCCESS_STATUS, SUCCESSFUL_REGISTER_MESSAGE, null);
                } else {
                    User u1 = this.userRepository.findByUserName(userDto.getUserName());
                    User u2 = this.userRepository.findByEmail(userDto.getEmail());
                    if (u1 != NULL_CHECK && u2 != NULL_CHECK) {
                        throw new BadRequestException(USER_WITH_USERNAME + u1.getUserName() + AND_EMAIL + u2.getEmail() + ALREADY_EXISTS);
                    } else if (u1 != NULL_CHECK) {
                        throw new BadRequestException(USER_WITH_USERNAME + u1.getUserName() + ALREADY_EXISTS);
                    } else if (u2 != NULL_CHECK) {
                        throw new BadRequestException(USER_WITH_EMAIL + u2.getEmail() + ALREADY_EXISTS);
                    }
                }
            }
            ApiResponse response = new ApiResponse(FAILURE_STATUS, VIOLATED_FIELD_MESSAGE, validations);
            logger.info("Exit from UserService.registration:{}", response);
            return response;
        }
        catch (NullPointerException ex){
            throw new BadRequestException(NULL_REQUEST);
        }
    }

}
