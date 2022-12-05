package com.example.demoproject.service.impl;

import com.example.demoproject.entity.SampleTest;
import com.example.demoproject.mapper.SampleTestMapper;
import com.example.demoproject.service.SampleTestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SampleTestServiceImpl implements SampleTestService {

    @Resource
    private SampleTestMapper mapper;

    @Override
    public List<SampleTest> selectAllSampleTest() {
        return mapper.selectAllSampleTest();
    }

    @Override
    public int insertSampleTest(SampleTest sample) {
        return mapper.insertSampleTest(sample);
    }
}
