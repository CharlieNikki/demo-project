package com.example.demoproject.mapper;

import com.example.demoproject.entity.Project;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper {

    int insertProject(Project project);
}
