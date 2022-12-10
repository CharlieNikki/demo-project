package com.example.demoproject.service.impl;

import com.example.demoproject.entity.Image;
import com.example.demoproject.mapper.ImageMapper;
import com.example.demoproject.service.ImageService;
import com.example.demoproject.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import static com.example.demoproject.constant.Sign.RETURN_MESSAGE_FAIL;

@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    private ImageMapper mapper;

    /**
     * 将图片存入本地和数据库
     *
     * @param image
     * @param file
     * @return
     */
    @Override
    @Transactional
    public boolean insertImage(Image image, MultipartFile file) {

        // 将图片存入本地
        String fileNewName = FileUtil.fileDownload(file);
        if (!fileNewName.equals(RETURN_MESSAGE_FAIL)) {
            // 将图片存入数据库
            image.setImageName(fileNewName);
            int insertResult = mapper.insertImage(image);
            // 存入数据库成功
            return insertResult == 1;
        }
        return false;
    }
}
