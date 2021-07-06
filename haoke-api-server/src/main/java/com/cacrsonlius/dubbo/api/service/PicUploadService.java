package com.cacrsonlius.dubbo.api.service;

import com.cacrsonlius.dubbo.api.config.QiNiuYun;
import com.cacrsonlius.dubbo.api.vo.PicUploadResult;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Service
public class PicUploadService {
    private static final String[] IMAGE_TYPE = new String[]{".jpg", ".bmp", ".jpeg", ".gig", ".png"};

    @Autowired
    private Auth auth;

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private QiNiuYun qiNiuYun;

    public PicUploadResult upload(MultipartFile multipartFile) {
        // 校验图片格式
        boolean isLegal = false;
        for (String s : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(multipartFile.getOriginalFilename(), s)) {
                isLegal = true;
                break;
            }
        }

        PicUploadResult picUploadResult = new PicUploadResult();

        if (!isLegal) {
            picUploadResult.setStatus("error");
            return picUploadResult;
        }

        String fileName = multipartFile.getOriginalFilename();
        String path = getFilePath(fileName);

        try {
            byte[] uploadBytes = multipartFile.getBytes();
            String upToken = auth.uploadToken(qiNiuYun.getBucket());
            Response response = uploadManager.put(uploadBytes, path, upToken);
            System.out.println("七牛云响应:"+ response);

        } catch (IOException ex) {
            ex.printStackTrace();
            picUploadResult.setStatus("error");

            return picUploadResult;
        }

        picUploadResult.setStatus("done");
        picUploadResult.setName(qiNiuYun.getUrlPrefix() + path);
        picUploadResult.setUid(String.valueOf(System.currentTimeMillis()));

        return picUploadResult;
    }

    private String getFilePath(String sourceFileName) {
        DateTime dateTime = new DateTime();
        return "images/" + dateTime.toString("yyyy")
                + "/" + dateTime.toString("MM") + "/"
                + dateTime.toString("dd") + "/" + System.currentTimeMillis() +
                RandomUtils.nextInt(100, 9999) + "." +
                StringUtils.substringAfterLast(sourceFileName, ".");
    }

}
