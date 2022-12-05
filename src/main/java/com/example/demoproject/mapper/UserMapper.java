package com.example.demoproject.mapper;

import com.example.demoproject.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    // 新增用户
    int insertUser(User user);

    // 通过手机号搜索用户（手机号唯一）
    User selectUserByPhone(String phone);

    int updateUserByPhone(String phone, String username, String companyName);
}
