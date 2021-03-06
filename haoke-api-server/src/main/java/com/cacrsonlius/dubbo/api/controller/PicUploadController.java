package com.cacrsonlius.dubbo.api.controller;

import com.cacrsonlius.dubbo.api.service.PicUploadFileSystemService;
import com.cacrsonlius.dubbo.api.service.PicUploadService;
import com.cacrsonlius.dubbo.api.vo.PicUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/pic/upload")
@RestController
public class PicUploadController {

    @Autowired
    private PicUploadService picUploadService;

    @Autowired
    private PicUploadFileSystemService picUploadFileSystemService;

    @PostMapping
    public PicUploadResult upload(@RequestParam("housePic")MultipartFile multipartFile){

        return picUploadFileSystemService.upload(multipartFile);
//        return picUploadService.upload(multipartFile);
    }

}
