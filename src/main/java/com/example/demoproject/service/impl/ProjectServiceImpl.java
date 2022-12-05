package com.example.demoproject.service.impl;

import com.example.demoproject.entity.Project;
import com.example.demoproject.mapper.ProjectMapper;
import com.example.demoproject.service.ProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public int deleteProjectById(Integer id) {
        return mapper.deleteProjectById(id);
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
