package com.app.RentWheelz.controllers;

import com.app.RentWheelz.payloads.ApiResponse;
import com.app.RentWheelz.payloads.UserDto;
import com.app.RentWheelz.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registration(@RequestBody UserDto userDto) {
        logger.info("Inside UserController.registration:{}", userDto);
        ApiResponse response = this.userService.registration(userDto);
        logger.info("Exit from UserController.registration");
        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }
}
