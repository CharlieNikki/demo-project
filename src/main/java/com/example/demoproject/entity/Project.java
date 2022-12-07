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

    private Integer id;             // id
    private String projectName;     // project_name
    private String username;        // leader_name
    private String telephone;       // leader_phone
    private String content;         // project_detail
    private String location;        // location
    private BigDecimal longitude;   // longitude
    private BigDecimal latitude;    // latitude
    private String uploader;        // uploader
    private String date;            // detect_date
    private Integer dataStatus;     // data_status
    private String address;         // address
    public void setProject(String projectName, String username, String telephone,
                           String content, String uploader, String date) {
        this.projectName = projectName;
        this.username = username;
        this.telephone = telephone;
        this.content = content;
        this.uploader = uploader;
        this.date = date;
    }
}
