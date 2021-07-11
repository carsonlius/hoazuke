package com.carsonlius.dubbo.server.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.carsonlius.dubbo.server.popj.Ad;
import com.carsonlius.dubbo.server.service.AdService;
import com.carsonlius.dubbo.server.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class ApiAdServiceImpl implements ApiAdService{

    @Autowired
    private AdService adService;

    @Override
    public PageInfo<Ad> queryAdList(Integer type, Integer page, Integer pageSize) {
        Ad ad = new Ad();
        ad.setType(type);

        return adService.queryAdList(ad, page, pageSize);
    }
}
