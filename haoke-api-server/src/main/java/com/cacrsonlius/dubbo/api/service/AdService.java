package com.cacrsonlius.dubbo.api.service;


import com.alibaba.dubbo.config.annotation.Reference;
import com.carsonlius.dubbo.server.api.ApiAdService;
import com.carsonlius.dubbo.server.popj.Ad;
import com.carsonlius.dubbo.server.vo.PageInfo;
import org.springframework.stereotype.Service;

@Service
public class AdService {
    @Reference(version = "1.0.0")
    private ApiAdService adService;

    public PageInfo<Ad> queryAdList(Integer type, Integer page, Integer pageSize) {
        return adService.queryAdList(type, page, pageSize);
    }
}
