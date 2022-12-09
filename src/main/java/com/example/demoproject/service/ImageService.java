package com.example.demoproject.service;

import com.example.demoproject.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    boolean insertImage(Image image, MultipartFile file) throws Exception;
}
