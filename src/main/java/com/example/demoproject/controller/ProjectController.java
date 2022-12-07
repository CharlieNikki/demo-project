package com.example.demoproject.controller;

import com.example.demoproject.entity.Location;
import com.example.demoproject.entity.Project;
import com.example.demoproject.entity.Result;
import com.example.demoproject.service.ProjectService;
import com.example.demoproject.utils.DateUtil;
import com.example.demoproject.utils.FileUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.util.List;

import static com.example.demoproject.constant.Sign.*;

@RestController
public class ProjectController {

    @Resource
    private ProjectService service;

    @PostMapping("/project/insert")
    @ResponseBody
    public Result insert(@RequestParam(value = "projectName", required = true) String projectName,
                                @RequestParam(value = "username", required = true) String username,
                                @RequestParam(value = "telephone", required = true) String phone,
                                @RequestParam(value = "content", required = true) String content,
                                @RequestParam(value = "location", required = false) Location location,
                                @RequestPart(value = "uploader", required = true)MultipartFile file) {

        Result result = new Result();
        Project project = new Project();
        String date = DateUtil.dateFormat();
        String newFileName;

        if (file != null) {
            try {
                // 将图片下载至本地
                newFileName = FileUtil.fileDownload(file);
                if (!newFileName.equals(RETURN_MESSAGE_FAIL)) {
                    // 将数据存入数据库
                    project.setProject(projectName, username, phone, content, newFileName, date);
                    if (location != null) {
                        project.setLongitude(location.getLng());
                        project.setLatitude(location.getLat());
                        project.setAddress(location.getAddr());
                    }
                    int insertResult = service.insertProject(project);
                    if (insertResult == 1) {
                        result.setResult(RETURN_CODE_SUCCESS, RETURN_MESSAGE_SUCCESS, null, insertResult);
                    } else {
                        result.setResult(RETURN_CODE_FAIL, "数据库更新失败", null, 0);
                    }
                } else {
                    result.setResult(RETURN_CODE_FAIL, "图片上传失败", null, 0);
                }
            } catch (Exception e) {
                result.setResult(SYSTEM_CODE_ERROR, e.getMessage(), null, null);
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 根据phone批量获取Project信息
     * @param phone
     * @return
     */
    @GetMapping("/project/getProject")
    @ResponseBody
    public Result getProject(@RequestParam(value = "phone") String phone) {

        Result result = new Result();
        List<Project> projects;

        try {
            projects = service.selectProjectByPhone(phone);
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
    public Result delete(@RequestParam(value = "id") Integer id) {

        Result result = new Result();
        try {
            boolean deleteResult = service.deleteProjectById(id);
            if (deleteResult) {
                result.setResult(RETURN_CODE_SUCCESS, RETURN_MESSAGE_SUCCESS, null, 1);
            } else {
                result.setResult(RETURN_CODE_FAIL, "数据库更新失败", null ,0);
            }
        } catch (Exception e) {
            result.setResult(SYSTEM_CODE_ERROR, e.getMessage(), null, null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取所有的project信息
     */
    @GetMapping("/project/getAll")
    @ResponseBody
    public Result getAll() {

        Result result = new Result();
        List<Project> projects;
        try {
            projects = service.selectAllProject();
            if (projects.size() != 0) {
                result.setResult(RETURN_CODE_SUCCESS, RETURN_MESSAGE_SUCCESS, projects, projects.size());
            } else {
                result.setResult(RETURN_CODE_FAIL, "没有什么信息", null, 0);
            }
        } catch (Exception e) {
            result.setResult(SYSTEM_CODE_ERROR, e.getMessage(), null, null);
        }
        return result;
    }
}
