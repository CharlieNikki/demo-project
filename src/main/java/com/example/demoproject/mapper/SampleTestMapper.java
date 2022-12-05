package com.example.demoproject.mapper;

import com.example.demoproject.entity.SampleTest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SampleTestMapper {

    int insertSampleTest(SampleTest sample);

    List<SampleTest> selectAllSampleTest();
}
