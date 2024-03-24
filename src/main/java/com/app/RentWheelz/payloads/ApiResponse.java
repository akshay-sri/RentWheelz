package com.app.RentWheelz.payloads;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiResponse {
    private String status;
    private String message;
    private List<String> ViolatedFields;
}
