package com.carsonlius.dubbo.server.api;


import com.carsonlius.dubbo.server.popj.Ad;
import com.carsonlius.dubbo.server.vo.PageInfo;

public interface ApiAdService {

    /**
     * 分页查询广告数据
     * @param type
     * @param page
     * @param pageSize
     * */
    PageInfo<Ad> queryAdList(Integer type, Integer page, Integer pageSize);
}
