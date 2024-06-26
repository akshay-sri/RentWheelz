package com.app.RentWheelz.controllers;

import com.app.RentWheelz.payloads.LoginRequest;
import com.app.RentWheelz.payloads.ApiResponse;
import com.app.RentWheelz.payloads.RegisterRequest;
import com.app.RentWheelz.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registration(@RequestBody RegisterRequest userDto) {
        logger.info("Inside UserController.registration:{}", userDto);
        ApiResponse response = this.userService.registration(userDto);
        logger.info("Exit from UserController.registration");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request){
        logger.info("Inside UserController.login:{}", request);
        ApiResponse response = this.userService.login(request);
        logger.info("Exit from UserController.login");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/myProfile/{username}")
    public ResponseEntity<RegisterRequest> myProfile(@PathVariable(name = "username") String username){
        logger.info("Inside UserController.myProfile:{}",username);
        RegisterRequest response = this.userService.myProfile(username);
        logger.info("Exit from UserController.myProfile");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
