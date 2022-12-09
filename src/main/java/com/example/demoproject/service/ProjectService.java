package com.example.demoproject.service;

import com.example.demoproject.entity.Project;

import java.util.List;

public interface ProjectService {

    int insertProject(Project project);

    List<Project> selectProjectByPhone(String phone);

    boolean deleteProjectById(Integer id);

    List<Project> selectAllProject();
}
