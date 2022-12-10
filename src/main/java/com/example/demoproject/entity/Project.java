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

    /*----实体类对应的属性名称----------数据库中对应的字段名---------*/
    private String id;              // id
    private String projectName;     // project_name
    private String username;        // leader_name
    private String telephone;       // leader_phone
    private String content;         // project_detail
    private BigDecimal lng;         // longitude
    private BigDecimal lat;         // latitude
    private String date;            // detect_date
    private Integer dataStatus;     // data_status
    private String addr;            // address
    private String userId;          // user_id
}
