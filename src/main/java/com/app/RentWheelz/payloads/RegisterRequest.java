package com.app.RentWheelz.payloads;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterRequest {
    private int userId;
    private String userName;
    private String email;
    private String password;
    private String proofId;
    private String firstName;
    private String lastName;
    private Character gender;
    private String mobileNumber;
}
