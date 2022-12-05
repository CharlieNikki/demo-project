package com.example.demoproject.service;

import com.example.demoproject.entity.User;

public interface UserService {

    int insertUser(User user);

    User selectUserByPhone(String phone);

    int updateUserByPhone(String phone, String username, String companyName);
}
