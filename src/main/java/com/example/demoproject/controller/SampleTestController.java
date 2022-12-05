package com.example.demoproject.controller;

import com.example.demoproject.entity.Result;
import com.example.demoproject.entity.SampleTest;
import com.example.demoproject.service.SampleTestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;

import static com.example.demoproject.constant.Sign.*;

@RestController
public class SampleTestController {

    @Resource
    private SampleTestService service;

    @PostMapping("/sample/insert")
    @ResponseBody
    public Result insert(SampleTest sample) {

        Result result = new Result();

        try {
            int insertResult = service.insertSampleTest(sample);
            if (insertResult == 1) {
                result.setResult(RETURN_CODE_SUCCESS, RETURN_MESSAGE_SUCCESS, null, insertResult);
            } else {
                result.setResult(RETURN_CODE_FAIL, RETURN_MESSAGE_FAIL, null, 0);
            }
        } catch (Exception e) {
            result.setResult(SYSTEM_CODE_ERROR, e.getMessage(), null, null);
        }
        return result;
    }

    @GetMapping("/sample/getAll")
    @ResponseBody
    public Result getAll() {

        Result result = new Result();

        try {
            List<SampleTest> list = service.selectAllSampleTest();
            if (list.size() != 0) {
                result.setResult(RETURN_CODE_SUCCESS, RETURN_MESSAGE_SUCCESS, list, list.size());
            } else {
                result.setResult(RETURN_CODE_FAIL, "没有什么数据", null, 0);
            }
        } catch (Exception e) {
            result.setResult(SYSTEM_CODE_ERROR, e.getMessage(), null, null);
        }
        return result;
    }
}
