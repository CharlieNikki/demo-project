package com.example.demoproject.service;

import com.example.demoproject.entity.SampleTest;

import java.util.List;

public interface SampleTestService {

    int insertSampleTest(SampleTest sample);

    List<SampleTest> selectAllSampleTest();
}
