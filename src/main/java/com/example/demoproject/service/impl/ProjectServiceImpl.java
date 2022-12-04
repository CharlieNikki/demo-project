package com.example.demoproject.service.impl;

import com.example.demoproject.entity.Project;
import com.example.demoproject.mapper.ProjectMapper;
import com.example.demoproject.service.ProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectMapper mapper;

    @Override
    public int insertProject(Project project) {
        return mapper.insertProject(project);
    }
}
