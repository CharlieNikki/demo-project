package com.example.demoproject.utils;

import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static com.example.demoproject.constant.Sign.RETURN_MESSAGE_FAIL;

public class FileUtil {

    /**
     * 文件下载
     */
    @SneakyThrows
    public static String fileDownload(MultipartFile file) {

        StringBuilder imagesPath = new StringBuilder();
        String newFileName;
        ImageUtil imageUtil = new ImageUtil();

        if (file != null) {

            // 获取文件后缀
            String suffixName = imageUtil.getImagePath(file);
            // 生成新文件的名称(UUID+时间戳)
            newFileName = imageUtil.getNewFileName(suffixName);
            // 保存文件
            File f = new File(imageUtil.getNewImagePath(newFileName));
            // 将对象存入本地磁盘
            boolean state = imageUtil.saveImage(file, f);
            // 存入本地磁盘成功
            if (state) {
                imagesPath.append(newFileName).append(",");
            } else {
                return RETURN_MESSAGE_FAIL;
            }
        }
        return String.valueOf(imagesPath.deleteCharAt(imagesPath.length() - 1));
    }
}
