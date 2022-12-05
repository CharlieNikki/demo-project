package com.example.demoproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SampleTest {

    private Integer id;
    private Integer projectId;
    private String orgName;
    private String address;
    private String contacts;
    private String phone;
    private String projectName;
    private String witness;
    private String certificateNumber;
    private String testName;
    private Integer testNumber;
    private String testType;
    private String testFactory;
    private Integer testOrgId;
    private String testUsing;
    private String testProject;
    private String testDependence;
    private Integer testHandling;
    private Integer reportDistribution;
    private String remark;
    private Integer sampleStatus;
}
