package com.example.demoproject.controller;

import com.example.demoproject.constant.Sign;
import com.example.demoproject.entity.Image;
import com.example.demoproject.entity.Result;
import com.example.demoproject.service.ImageService;
import com.example.demoproject.utils.DateUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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
    public Result insertImg(@RequestPart("uploader")MultipartFile file,
                            @RequestParam("projectId") Integer projectId) {

        Result result = new Result();
        Image image = new Image();
        boolean flag = false;
        image.setImageType(0);
        image.setDate(DateUtil.dateFormat());
        image.setProjectId(projectId);

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
