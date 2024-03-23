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

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public ApiResponse registration(UserDto userDto) {
        logger.info("Inside UserService.registration");
        List<String> validations = UserHelper.validateUser(userDto);
        if (validations.isEmpty()) {
            if (userRepository.findByUserName(userDto.getUserName()) == null && userRepository.findByEmail(userDto.getEmail()) == null) {
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
                return new ApiResponse("Success!", "User registered successfully",null);
            } else {
                User u1 = this.userRepository.findByUserName(userDto.getUserName());
                User u2 = this.userRepository.findByEmail(userDto.getEmail());
                if (u1 != null && u2 != null) {
                    throw new BadRequestException("User with userName: " + u1.getUserName() + " and Email: " + u2.getEmail() + " already exists");
                } else if (u1 != null) {
                    throw new BadRequestException("User with userName: " + u1.getUserName() + " already exists");
                } else if (u2 != null) {
                    throw new BadRequestException("User with email: " + u2.getEmail() + " already exists");
                }
            }
        }
        ApiResponse response = new ApiResponse("Failure", "There are some violated fields",validations);
        logger.info("Exit from UserService.registration:{}",response);
        return response;
    }

}
