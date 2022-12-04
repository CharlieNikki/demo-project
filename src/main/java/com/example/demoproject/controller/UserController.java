package com.example.demoproject.controller;

import com.example.demoproject.constant.Sign;
import com.example.demoproject.entity.Result;
import com.example.demoproject.entity.User;
import com.example.demoproject.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.example.demoproject.constant.Sign.*;

@RestController
public class UserController {

    @Resource
    private UserService service;

    /**
     * 用户注册
     */
    @PostMapping("/user/register")
    @ResponseBody
    public Result register(User user) {

        Result result = new Result();
        try {
            if (service.selectUserByPhone(user.getPhone()) == null) {
                service.insertUser(user);
                result.setCode(RETURN_CODE_SUCCESS);
                result.setMsg(RETURN_MESSAGE_SUCCESS);
            } else {
                result.setCode(RETURN_CODE_FAIL);
                result.setMsg("该手机号已被使用！");
            }
        } catch (Exception e) {
            result.setCode(SYSTEM_CODE_ERROR);
            result.setMsg(e.getMessage());
        }

        return result;
    }

    @PostMapping("/user/login")
    @ResponseBody
    public Result login(@RequestParam(value = "phone") String phone,
                        @RequestParam(value = "password") String password) {

        Result result = new Result();
        User user;
        try {
            user = service.selectUserByPhone(phone);
            System.out.println(user);
            if (user == null) {
                result.setCode(RETURN_CODE_FAIL);
                result.setMsg("该用户不存在");
            } else {
                // 进行密码的匹配
                if (user.getPassword().equals(password)) {
                    // 登陆成功
                    result.setCode(RETURN_CODE_SUCCESS);
                    result.setMsg("登陆成功");
                    user.setPassword("");
                    result.setData(user);
                } else {
                    result.setCode(RETURN_CODE_FAIL);
                    result.setMsg("密码错误");
                }
            }
        } catch (Exception e) {
            result.setCode(SYSTEM_CODE_ERROR);
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     *  获取个人资料
     */
    @GetMapping("/user/getUserInfo")
    @ResponseBody
    public Result getUserInfo(String phone) {

        return null;
    }
}
