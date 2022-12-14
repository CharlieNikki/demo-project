package com.example.demoproject.controller;

import com.example.demoproject.constant.Sign;
import com.example.demoproject.entity.Image;
import com.example.demoproject.entity.Result;
import com.example.demoproject.service.ImageService;
import com.example.demoproject.utils.DateUtil;
import com.example.demoproject.utils.SnowflakeIdWorker;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import static com.example.demoproject.constant.Sign.*;

@RestController
public class ImageController {

    @Resource
    private ImageService service;

    /**
     * 根据projectId进行图片的上传
     * @param file
     * @param projectId
     * @return
     */
    @PostMapping("/project/insertImg")
    @ResponseBody
    public Result insertImg(@RequestParam("uploader") MultipartFile file,
                            @RequestParam("projectId") String projectId) {

        Result result = new Result();
        Image image = new Image();
        SnowflakeIdWorker snow = new SnowflakeIdWorker(0, 0);
        boolean flag = false;

        image.setImageType(0);
        image.setDate(DateUtil.dateFormat());
        image.setProjectId(projectId);
        image.setId(String.valueOf(snow.nextId()));

        // 判断文件是否为空
        if (file != null) {
            try {
                flag = service.insertImage(image, file);
            } catch (Exception e) {
                result.setResult(SYSTEM_CODE_ERROR, e.getMessage(), null, 0);
                e.printStackTrace();
            }
            if (flag) {
                // 存入本地和数据库成功
                result.setResult(RETURN_CODE_SUCCESS, RETURN_MESSAGE_SUCCESS, null, 1);
            } else {
                result.setResult(RETURN_CODE_FAIL, "图片上传失败", null, 0);
            }
        }
        return result;
    }
}
