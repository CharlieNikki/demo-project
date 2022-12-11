package com.example.demoproject.mapper;

import com.example.demoproject.entity.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {

    int insertProject(Project project);

    List<Project> selectProjectByPhone(String phone);

    int deleteProjectById(String id);

    List<Project> selectAllProject();

    List<Project> selectProjectByUserId(String userId);

    List<Project> selectProjectByStatus(Integer dataStatus, String userId);
}
