package com.carsonlius.dubbo.server.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.carsonlius.dubbo.server.popj.HouseResources;
import com.carsonlius.dubbo.server.popj.MongoHouse;
import com.carsonlius.dubbo.server.service.HouseResourcesService;
import com.carsonlius.dubbo.server.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


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

    @Override
    public HouseResources queryHouseResourcesById(Long id) {
        return houseResourcesService.queryHouseResourcesById(id) ;
    }

    @Override
    public boolean updateHouseResources(HouseResources houseResources) {
        return houseResourcesService.updateHouseResources(houseResources);
    }

    @Override
    public List<MongoHouse> searchHouse(float lng, float lat, int zoom) {
        return houseResourcesService.searchHouse(lng, lat, zoom);
    }
}
