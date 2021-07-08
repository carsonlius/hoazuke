package com.carsonlius.dubbo.server.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.carsonlius.dubbo.server.popj.HouseResources;
import com.carsonlius.dubbo.server.service.HouseResourcesService;
import com.carsonlius.dubbo.server.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;


@Service(version = "1.0.0")
public class ApiHouseResourcesServiceImpl implements ApiHouseResourcesService {
    @Autowired
    private HouseResourcesService houseResourcesService;

    @Override
    public int saveHouseResources(HouseResources houseResources) {
        return houseResourcesService.saveHouseResources(houseResources);
    }

    @Override
    public PageInfo<HouseResources> queryHouseResourcesList(int pageNo, int pageSize, HouseResources queryCondition) {
        return houseResourcesService.queryHouseResourcesList(pageNo,pageSize,queryCondition);
    }
}
