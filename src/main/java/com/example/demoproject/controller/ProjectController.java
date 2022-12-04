package com.example.demoproject.controller;

import com.example.demoproject.entity.Location;
import com.example.demoproject.entity.Project;
import com.example.demoproject.entity.Result;
import com.example.demoproject.service.ProjectService;
import com.example.demoproject.utils.FileUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import static com.example.demoproject.constant.Sign.*;

@RestController
public class ProjectController {

    @Resource
    private ProjectService service;

    @PostMapping("/insertProject")
    @ResponseBody
    public Result insertProject(@RequestParam(value = "projectName", required = true) String projectName,
                                @RequestParam(value = "username", required = true) String username,
                                @RequestParam(value = "telephone", required = true) String phone,
                                @RequestParam(value = "content", required = true) String content,
                                @RequestParam(value = "location", required = false) Location location,
                                @RequestPart(value = "uploader", required = true)MultipartFile file) {

        Result result = new Result();
        Project project = new Project();
        String newFileName;

        if (file != null) {
            try {
                // 将图片下载至本地
                newFileName = FileUtil.fileDownload(file);
                if (!newFileName.equals(RETURN_MESSAGE_FAIL)) {
                    // 将数据存入数据库
                    project.setProjectName(projectName);
                    project.setUsername(username);
                    project.setTelephone(phone);
                    project.setContent(content);
                    project.setUploader(newFileName);

                    if (location != null) {
                        project.setLongitude(location.getLng());
                        project.setLatitude(location.getLat());
                    }
                    if (service.insertProject(project) == 1) {
                        result.setCode(RETURN_CODE_SUCCESS);
                        result.setMsg(RETURN_MESSAGE_SUCCESS);
                    } else {
                        result.setCode(RETURN_CODE_FAIL);
                        result.setMsg("数据库更新失败");
                    }
                } else {
                    result.setCode(RETURN_CODE_FAIL);
                    result.setMsg("图片上传失败");
                }
            } catch (Exception e) {
                result.setCode(SYSTEM_CODE_ERROR);
                result.setMsg(e.getMessage());
            }
        }
        return result;
    }
}
