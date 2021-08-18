package com.cacrsonlius.dubbo.api.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cacrsonlius.dubbo.api.vo.Pagination;
import com.cacrsonlius.dubbo.api.vo.TableResult;
import com.carsonlius.dubbo.server.api.ApiHouseResourcesService;
import com.carsonlius.dubbo.server.popj.HouseResources;
import com.carsonlius.dubbo.server.popj.MongoHouse;
import com.carsonlius.dubbo.server.vo.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseResourcesService {
    @Reference(version = "1.0.0")
    private ApiHouseResourcesService apiHouseResourcesService;

    public boolean save(HouseResources houseResources) {

        return apiHouseResourcesService.saveHouseResources(houseResources) == 1;
    }

    public TableResult<HouseResources> queryList(HouseResources houseResources, Integer pageNo, Integer pageSize) {
        PageInfo<HouseResources> pageInfo = apiHouseResourcesService.queryHouseResourcesList(pageNo, pageSize, houseResources);
        List<HouseResources> houseResourcesList = pageInfo.getRecords();
        Pagination pagination = new Pagination(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal());
        return new TableResult<HouseResources>(houseResourcesList, pagination);
    }

    public HouseResources queryHouseResourceById(Long id) {
        return apiHouseResourcesService.queryHouseResourcesById(id);
    }

    public boolean updateHouseResources(HouseResources houseResources) {
        return apiHouseResourcesService.updateHouseResources(houseResources);
    }

    public List<MongoHouse> searchHouse(float lng, float lat, int zoom){
        return apiHouseResourcesService.searchHouse(lng, lat, zoom);
    }
}
