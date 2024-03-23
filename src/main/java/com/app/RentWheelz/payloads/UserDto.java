package com.app.RentWheelz.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
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
