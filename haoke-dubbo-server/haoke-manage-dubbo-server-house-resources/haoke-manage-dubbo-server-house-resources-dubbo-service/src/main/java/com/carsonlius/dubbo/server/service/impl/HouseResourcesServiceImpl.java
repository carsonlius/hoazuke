package com.carsonlius.dubbo.server.service.impl;

import com.carsonlius.dubbo.server.popj.HouseResources;
import com.carsonlius.dubbo.server.service.HouseResourcesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class HouseResourcesServiceImpl extends BaseServiceImpl<HouseResources> implements HouseResourcesService {

    /**
     *
     * @param houseResources
     *
     * @return -1:输入的参数不符合要求，0：数据插入数据库失败，1：成功
     */
    @Override
    public int saveHouseResources(HouseResources houseResources) {
        // 添加校验
        if (StringUtils.isEmpty(houseResources.getTitle())) {
            return -1;
        }

        return super.save(houseResources);
    }
}
