package com.example.demoproject.controller;

import com.example.demoproject.entity.Result;
import com.example.demoproject.entity.User;
import com.example.demoproject.service.UserService;
import com.example.demoproject.utils.SnowflakeIdWorker;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.example.demoproject.constant.Sign.*;

@RestController
public class UserController {

    @Resource
    private UserService service;

    /**
     * 注册
     */
    @PostMapping("/user/register")
    @ResponseBody
    public Result register(User user) {

        Result result = new Result();
        SnowflakeIdWorker snow = new SnowflakeIdWorker(0,0);
        user.setUserId(String.valueOf(snow.nextId()));
        user.setUserType(0);

        try {
            if (service.selectUserByPhone(user.getPhone()) == null) {
                int insertResult = service.insertUser(user);
                result.setResult(RETURN_CODE_SUCCESS, RETURN_MESSAGE_SUCCESS, null, insertResult);
            } else {
                result.setResult(RETURN_CODE_FAIL, "该手机号已被使用", null, null);
            }
        } catch (Exception e) {
            result.setResult(SYSTEM_CODE_ERROR, e.getMessage(), null, null);
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 登录
     */
    @PostMapping("/user/login")
    @ResponseBody
    public Result login(@RequestParam(value = "phone") String phone,
                        @RequestParam(value = "password") String password) {

        Result result = new Result();
        User user;

        try {
            user = service.selectUserByPhone(phone);
            if (user == null) {
                result.setResult(RETURN_CODE_FAIL, "没有该用户", null, null);
            } else {
                // 进行密码的匹配
                if (user.getPassword().equals(password)) {
                    // 登陆成功
                    user.setPassword("");
                    result.setResult(RETURN_CODE_SUCCESS, "登陆成功", user, 1);
                } else {
                    result.setResult(RETURN_CODE_FAIL, "密码错误", null, 0);
                }
            }
        } catch (Exception e) {
            result.setResult(SYSTEM_CODE_ERROR, e.getMessage(), null, null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     *  获取个人资料
     */
    @GetMapping("/user/getUser")
    @ResponseBody
    public Result getUser(@RequestParam(value = "phone") String phone) {

        Result result = new Result();
        User user;

        try {
            user = service.selectUserByPhone(phone);
            if (user != null) {
                result.setResult(RETURN_CODE_SUCCESS, RETURN_MESSAGE_SUCCESS, user, 1);
            } else {
                result.setResult(RETURN_CODE_FAIL, "该用户不存在", null, 0);
            }
        } catch (Exception e) {
            result.setResult(SYSTEM_CODE_ERROR, e.getMessage(), null, null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新个人资料
     */
    @PostMapping("/user/update")
    @ResponseBody
    public Result updateUser(@RequestParam(value = "username") String username,
                             @RequestParam(value = "companyName") String companyName,
                             @RequestParam(value = "phone") String phone) {

        Result result = new Result();

        try {
            int updateResult = service.updateUserByPhone(phone, username, companyName);
            if (updateResult == 1) {
                // 更新成功
                result.setResult(RETURN_CODE_SUCCESS, RETURN_MESSAGE_SUCCESS, null, updateResult);
            } else {
                result.setResult(RETURN_CODE_FAIL, "数据库更新失败", null, 0);
            }
        } catch (Exception e) {
            result.setResult(SYSTEM_CODE_ERROR, e.getMessage(), null, null);
            e.printStackTrace();
        }
        return result;
    }

}
