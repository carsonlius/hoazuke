package com.carsonlius.dubbo.server.service;

import com.carsonlius.dubbo.server.popj.Ad;
import com.carsonlius.dubbo.server.vo.PageInfo;

public interface AdService {
    /**
     * 分页查询广告数据
     * @param ad
     * @param page
     * @param pageSize
     * */
    PageInfo<Ad> queryAdList(Ad ad, Integer page, Integer pageSize);
}
