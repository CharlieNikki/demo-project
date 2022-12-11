package com.example.demoproject.mapper;

import com.example.demoproject.entity.Image;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {

    int insertImage(Image image);

    List<String> selectImageNameByProjectId(String projectId);

    int deleteImageByProjectId(Integer projectId);
}
