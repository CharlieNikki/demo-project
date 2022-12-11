package com.example.demoproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Image {

    private String id;
    private String projectId;
    private String imageName;
    private Integer imageType;
    private String date;
}
