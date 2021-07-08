package com.carsonlius.dubbo.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.carsonlius.dubbo.server.popj.HouseResources;
import com.carsonlius.dubbo.server.service.HouseResourcesService;
import com.carsonlius.dubbo.server.vo.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class  HouseResourcesServiceImpl extends BaseServiceImpl<HouseResources> implements HouseResourcesService {

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

    @Override
    public PageInfo<HouseResources> queryHouseResourcesList(int pageNo, int pageSize, HouseResources queryCondition) {

        QueryWrapper<HouseResources> queryWrapper = new QueryWrapper<>(queryCondition);
        queryWrapper.orderByDesc("updated");
        IPage<HouseResources> iPage = queryPageList(queryWrapper,pageNo, pageSize);

        int total = Math.toIntExact(iPage.getTotal());
        int pageNum = (int) iPage.getCurrent();
        List<HouseResources> houseResourcesList = iPage.getRecords();

        return new PageInfo<HouseResources>(total, pageNum, pageSize, houseResourcesList);
    }
}
