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

    private Long id;
    private Long projectId;
    private String imageName;
    private Integer imageType;
    private String date;
}
