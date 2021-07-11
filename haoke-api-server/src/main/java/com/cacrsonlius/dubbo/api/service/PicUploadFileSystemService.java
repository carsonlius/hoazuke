package com.cacrsonlius.dubbo.api.service;

import com.cacrsonlius.dubbo.api.vo.PicUploadResult;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class PicUploadFileSystemService {
    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".gif", ".png"};


    public PicUploadResult upload(MultipartFile multipartFile) {
        boolean isLegal = false;
        for (String s : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(multipartFile.getOriginalFilename(), s)) {
                isLegal = true;
                break;
            }
        }

        PicUploadResult picUploadResult = new PicUploadResult();

        // 不合法
        if (!isLegal) {
            picUploadResult.setStatus("error");
            return picUploadResult;
        }

        // 新文件路径
        String filename = multipartFile.getOriginalFilename();
        String filePath = getFilePath(filename);

        // 生成图片的绝对引用路径
//        String picUrl = StringUtils.replace(StringUtils.substringAfter(filePath, "L:\\project\\javaPlatform\\hoazuke\\haoke-api-server\\src\\main\\resources\\pics"), "\\", "/");
        picUploadResult.setName(filePath);

        File newFile = new File(filePath);

        try {
            multipartFile.transferTo(newFile);


        } catch (IOException e) {
            e.printStackTrace();

            picUploadResult.setStatus("error");
            return picUploadResult;
        }

        picUploadResult.setStatus("done");
        picUploadResult.setUid(String.valueOf(System.currentTimeMillis()));

        return picUploadResult;
    }

    private String getFilePath(String sourceFileName) {
        String baseFolder = "L:\\project\\javaPlatform\\hoazuke\\haoke-api-server\\src\\main\\resources\\images";

        Date nowDate = new Date();
    // yyyy/MM/dd
        String fileFolder = baseFolder + File.separator + new
                DateTime(nowDate).toString("yyyy")
                + File.separator + new DateTime(nowDate).toString("MM") +
                File.separator
                + new DateTime(nowDate).toString("dd");
        File file = new File(fileFolder);

        if (!file.isDirectory()) {
            // 如果目录不存在，则创建目录
            file.mkdirs();
        }

        // 生成新的文件名
        String fileName = new DateTime(nowDate).toString("yyyyMMddhhmmssSSSS")
                + RandomUtils.nextInt(100, 9999) + "." +
                StringUtils.substringAfterLast(sourceFileName, ".");
        return fileFolder + File.separator + fileName;
    }
}
