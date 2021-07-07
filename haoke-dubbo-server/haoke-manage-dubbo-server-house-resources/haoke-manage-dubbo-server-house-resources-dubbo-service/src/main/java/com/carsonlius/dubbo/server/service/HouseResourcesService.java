package com.carsonlius.dubbo.server.service;

import com.carsonlius.dubbo.server.popj.HouseResources;
import com.carsonlius.dubbo.server.vo.PageInfo;

public interface HouseResourcesService {
    /**
     *
     * @param houseResources
     *
     * @return -1:输入的参数不符合要求，0：数据插入数据库失败，1：成功
     */
    int saveHouseResources(HouseResources houseResources);

    /***
     * @param pageNo 页吗
     * @param pageSize 每页条数
     * @param queryCondition 查询条件
     */
    PageInfo<HouseResources> queryHouseResourcesList(int pageNo, int pageSize, HouseResources queryCondition);

}
