package com.app.RentWheelz.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
