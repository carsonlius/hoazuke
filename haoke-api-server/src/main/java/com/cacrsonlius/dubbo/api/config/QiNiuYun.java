package com.cacrsonlius.dubbo.api.config;

import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "qiniuyun")
@Data
public class QiNiuYun {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String urlPrefix;

    @Bean
    public Auth auth(){
       return Auth.create(accessKey, secretKey);
    }

    @Bean
    public UploadManager uploadManager(){
        //构造一个带指定 Region 对象的配置类
        com.qiniu.storage.Configuration cfg = new com.qiniu.storage.Configuration(Region.region1());

        return new UploadManager(cfg);
    }
}
