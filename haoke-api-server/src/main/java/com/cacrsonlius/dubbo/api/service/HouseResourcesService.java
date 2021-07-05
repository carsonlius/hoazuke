package com.cacrsonlius.dubbo.api.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.carsonlius.dubbo.server.api.ApiHouseResourcesService;
import com.carsonlius.dubbo.server.popj.HouseResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseResourcesService {
    @Reference(version = "1.0.0")
    private ApiHouseResourcesService apiHouseResourcesService;

    public boolean save(HouseResources houseResources){

        return apiHouseResourcesService.saveHouseResources(houseResources) == 1;
    }

}
