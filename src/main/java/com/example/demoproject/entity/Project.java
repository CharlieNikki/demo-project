package com.example.demoproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Project {

    private Integer id;
    private String projectName;
    private String username;
    private String telephone;
    private String content;
    private String location;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String uploader;

}
