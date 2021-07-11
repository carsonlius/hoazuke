package com.carsonlius.dubbo.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.carsonlius.dubbo.server.popj.Ad;
import com.carsonlius.dubbo.server.service.AdService;
import com.carsonlius.dubbo.server.service.BaseServiceImpl;
import com.carsonlius.dubbo.server.vo.PageInfo;
import org.springframework.stereotype.Service;

@Service
public class AdServiceImpl extends BaseServiceImpl<Ad> implements AdService {
    @Override
    public PageInfo<Ad> queryAdList(Ad ad, Integer page, Integer pageSize) {
        QueryWrapper<Ad> queryWrapper = new QueryWrapper<>(ad);
        queryWrapper.orderByDesc("updated");


        IPage<Ad> iPage = super.queryPageList(queryWrapper, page, pageSize);
        PageInfo<Ad> pageInfo = new PageInfo<>();
        pageInfo.setTotal(Math.toIntExact(iPage.getTotal()));
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(pageSize);
        pageInfo.setRecords(iPage.getRecords());
        return pageInfo;
    }
}
