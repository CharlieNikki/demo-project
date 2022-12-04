package com.example.demoproject.service.impl;

import com.example.demoproject.entity.User;
import com.example.demoproject.mapper.UserMapper;
import com.example.demoproject.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper mapper;

    @Override
    public int insertUser(User user) {
        return mapper.insertUser(user);
    }

    @Override
    public User selectUserByPhone(String phone) {
        return mapper.selectUserByPhone(phone);
    }
}
