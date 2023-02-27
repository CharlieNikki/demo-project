package com.example.demoproject.service;

import com.example.demoproject.entity.Project;

import java.util.List;

public interface ProjectService {

    boolean insertProject(Project project);

    List<Project> selectProjectByPhone(String phone);

    boolean deleteProjectById(String id);

    List<Project> selectAllProject();

    List<Project> selectProjectByUserId(String userId);

    List<Project> selectProjectByStatus(Integer dataStatus, String userId);
}
