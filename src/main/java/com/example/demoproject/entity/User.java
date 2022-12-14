package com.example.demoproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private String userId;
    private String username;
    private String password;
    private String phone;
    private String companyName;
    private Integer userType;
}
