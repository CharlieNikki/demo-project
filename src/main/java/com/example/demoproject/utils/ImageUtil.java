package com.example.demoproject.utils;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Configuration
public class ImageUtil {


    //@Value("${path.save-image-path}")// 创建文件夹
    //public String SAVE_IMAGE_PATH = "D:/project/images/";


    //@Value("${path.save-image-path}")
    public String SAVE_IMAGE_PATH = "/usr/local/project/images/";

    /**
     * 返回文件后缀
     */
    public String getImagePath(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        int index = fileName.indexOf(".");
        return fileName.substring(index, fileName.length());
    }

    /**
     * 保存图片
     */
    @SneakyThrows
    public boolean saveImage(MultipartFile multipartFile, File file) {

        // 查看文件是否存在，不存在则创建
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            // 使用此方法必须要绝对路径且文件夹必须已存在，否则会报错
            multipartFile.transferTo(file);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 新文件名
     */
    public String getNewFileName(String suffix) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        return date + UUID.randomUUID().toString().replaceAll("-","") +  suffix;
    }

    /**
     * 返回图片保存地址
     */
    public String getNewImagePath(String name) {
        return SAVE_IMAGE_PATH + name;
    }
}
