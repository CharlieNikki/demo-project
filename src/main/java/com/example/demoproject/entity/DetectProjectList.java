package com.example.demoproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetectProjectList {

    private String projectName;
    private String username;
    private String telephone;
    private String content;
    private String date;
    private String datastatus;
    private String projectId;
}
