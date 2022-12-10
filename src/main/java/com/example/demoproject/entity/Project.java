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

    private String id;              // id
    private String projectName;     // project_name
    private String username;        // leader_name
    private String telephone;       // leader_phone
    private String content;         // project_detail
    private BigDecimal lng;   // longitude
    private BigDecimal lat;    // latitude
    private String date;            // detect_date
    private Integer dataStatus;     // data_status
    private String addr;         // address

    public void setProject(String id, String projectName, String username, String telephone,
                           String content, String date) {
        this.id = id;
        this.projectName = projectName;
        this.username = username;
        this.telephone = telephone;
        this.content = content;
        this.date = date;
    }
}
