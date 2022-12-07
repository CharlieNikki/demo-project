package com.example.demoproject.service.impl;

import com.example.demoproject.entity.Project;
import com.example.demoproject.mapper.ProjectMapper;
import com.example.demoproject.service.ProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectMapper mapper;

    @Override
    public List<Project> selectAllProject() {
        return mapper.selectAllProject();
    }

    @Override
    public boolean deleteProjectById(Integer id) {
        // 删除成败标识
        boolean flag = false;
        String uploader = mapper.selectProjectImageById(id);

        // 拼接成路径地址
        // Windows
        String uploaderPath = "E:/project/images/" + uploader;
        // Linux
        //String uploaderPath = "/usr/local/project/images/" + uploader;

        // 根据文件路径创建文件对象
        File file = new File(uploaderPath);
        // 该文件不是文件夹且不为空时
        if (file.isFile() && file.exists()) {
            // 从数据库中删除相关信息
            int deleteResult = mapper.deleteProjectById(id);
            if (deleteResult == 1) {
                // 数据库删除成功后，删除本地对应的图片信息
                flag = file.delete();
            }
        }
        return flag;
    }

    @Override
    public int insertImgById(Integer id, String uploader) {
        return mapper.updateImgById(id, uploader);
    }

    @Override
    public List<Project> selectProjectByPhone(String phone) {
        return mapper.selectProjectByPhone(phone);
    }

    @Override
    public int insertProject(Project project) {
        return mapper.insertProject(project);
    }
}
