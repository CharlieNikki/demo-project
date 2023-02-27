package com.example.demoproject.controller;

import com.example.demoproject.entity.Project;
import com.example.demoproject.entity.Result;
import com.example.demoproject.service.ProjectService;
import com.example.demoproject.utils.DateUtil;
import com.example.demoproject.utils.OrderNumUtil;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;

import java.util.List;

import static com.example.demoproject.constant.Sign.*;

@RestController
public class ProjectController {

    @Resource
    private ProjectService service;

    /**
     * 新增project
     */
    @PostMapping("/project/insert")
    public Result insertTest(Project project) {

        Result result = new Result();
        // 注入project的id
        project.setId(OrderNumUtil.getOrderId());
        // 设置project创建日期
        project.setDate(DateUtil.dateFormat());
        boolean insertResult = false;
        try {
            insertResult = service.insertProject(project);
        } catch (Exception e) {
            result.setResult(SYSTEM_CODE_ERROR, e.getMessage(), null, 0);
            e.printStackTrace();
        }

        if (insertResult) {
            // project新增时，返回project信息
            result.setResult(RETURN_CODE_SUCCESS, RETURN_MESSAGE_SUCCESS, project, 1);
        } else
            result.setResult(RETURN_CODE_FAIL, "数据库更新失败", null, 0);
        return result;
    }

    /**
     * 根据userId获取Project信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/project/getProject")
    @ResponseBody
    public Result getProject(@RequestParam(value = "userId") String userId) {

        Result result = new Result();
        List<Project> projects;

        try {
            projects = service.selectProjectByUserId(userId);
            if (projects.size() != 0) {
                result.setResult(RETURN_CODE_SUCCESS, RETURN_MESSAGE_SUCCESS, projects, projects.size());
            } else {
                result.setResult(RETURN_CODE_FAIL, "您没有预约的工程", null, 0);
            }
        } catch (Exception e) {
            result.setResult(SYSTEM_CODE_ERROR, e.getMessage(), null, null);
        }
        return result;
    }

    /**
     * 取消申请
     */
    @PostMapping("/project/delete")
    @ResponseBody
    public Result delete(@RequestParam(value = "id") String id) {

        Result result = new Result();
        try {
            boolean deleteResult = service.deleteProjectById(id);
            if (deleteResult) {
                result.setResult(RETURN_CODE_SUCCESS, RETURN_MESSAGE_SUCCESS, null, 1);
            } else {
                result.setResult(RETURN_CODE_FAIL, "数据库更新失败", null, 0);
            }
        } catch (Exception e) {
            result.setResult(SYSTEM_CODE_ERROR, e.getMessage(), null, null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据userId和检测状态(不采取手动输入)获取project信息
     */
    @GetMapping("/project/getProjectByStatus")
    public Result getProjectByStatus(@RequestParam(value = "userId") String userId) {

        Result result = new Result();
        List<Project> projects = null;
        try {
            // 搜索
            projects = service.selectProjectByStatus(3, userId);
        } catch (Exception e) {
            result.setResult(SYSTEM_CODE_ERROR, e.getMessage(), null, 0);
            e.printStackTrace();
        }
        // 搜索结果不为空
        if (projects != null) {
            if (projects.size() != 0) {
                result.setResult(RETURN_CODE_SUCCESS, RETURN_MESSAGE_SUCCESS, projects, projects.size());
            } else {
                result.setResult(RETURN_CODE_FAIL, "没有数据", null, 0);
            }
        }
        return result;
    }
}
