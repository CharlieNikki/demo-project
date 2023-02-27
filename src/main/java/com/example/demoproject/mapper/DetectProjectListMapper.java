package com.example.demoproject.mapper;

import com.example.demoproject.entity.DetectProjectList;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DetectProjectListMapper {

    /**
     * 新增detectProjectList信息
     * @param detectProjectList
     * @return
     */
    public int insertDetectProjectList(DetectProjectList detectProjectList);
}
