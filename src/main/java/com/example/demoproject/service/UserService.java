package com.example.demoproject.service;

import com.example.demoproject.entity.User;

public interface UserService {

    int insertUser(User user);

    User selectUserByPhone(String phone);
}
