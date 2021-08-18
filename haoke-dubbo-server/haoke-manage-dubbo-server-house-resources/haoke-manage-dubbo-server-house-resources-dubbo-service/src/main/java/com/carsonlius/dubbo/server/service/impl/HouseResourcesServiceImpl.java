package com.carsonlius.dubbo.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.carsonlius.dubbo.server.popj.HouseResources;
import com.carsonlius.dubbo.server.popj.MongoHouse;
import com.carsonlius.dubbo.server.service.BaseServiceImpl;
import com.carsonlius.dubbo.server.service.HouseResourcesService;
import com.carsonlius.dubbo.server.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class HouseResourcesServiceImpl extends BaseServiceImpl<HouseResources> implements HouseResourcesService {
    @Autowired
    private MongoTemplate mongoTemplate;

    private static final Map<Integer, Double> BAIDU_ZOOM = new HashMap<>();
    static {
        BAIDU_ZOOM.put(19, 20d / 1000); //单位为km
        BAIDU_ZOOM.put(18, 50d / 1000);
        BAIDU_ZOOM.put(17, 100d / 1000);
        BAIDU_ZOOM.put(16, 200d / 1000);
        BAIDU_ZOOM.put(15, 500d / 1000);
        BAIDU_ZOOM.put(14, 1d);
        BAIDU_ZOOM.put(13, 2d);
        BAIDU_ZOOM.put(12, 5d);
        BAIDU_ZOOM.put(11, 10d);
        BAIDU_ZOOM.put(10, 20d);
        BAIDU_ZOOM.put(9, 25d);
        BAIDU_ZOOM.put(8, 50d);
        BAIDU_ZOOM.put(7, 100d);
        BAIDU_ZOOM.put(6, 200d);
        BAIDU_ZOOM.put(5, 500d);
        BAIDU_ZOOM.put(4, 1000d);
        BAIDU_ZOOM.put(3, 2000d);
        BAIDU_ZOOM.put(2, 5000d);
        BAIDU_ZOOM.put(1, 10000d);
    }

    /**
     * @param houseResources
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
        IPage<HouseResources> iPage = queryPageList(queryWrapper, pageNo, pageSize);

        int total = Math.toIntExact(iPage.getTotal());
        int pageNum = (int) iPage.getCurrent();
        List<HouseResources> houseResourcesList = iPage.getRecords();

        return new PageInfo<HouseResources>(total, pageNum, pageSize, houseResourcesList);
    }

    @Override
    public HouseResources queryHouseResourcesById(Long id) {
        return super.queryById(id);
    }

    /**
     * 更新房源
     *
     * @param houseResources
     */
    @Override
    public boolean updateHouseResources(HouseResources houseResources) {
        return super.update(houseResources) != 0;
    }

    @Override
    public List<MongoHouse> searchHouse(float lng, float lat, int zoom) {

        double maxDistance = BAIDU_ZOOM.get(zoom)*1.5/111.2;
        Criteria criteria = Criteria
                .where("loc")
                .near(new Point(lng, lat))
                .maxDistance(maxDistance);
        Query query = Query.query(criteria);

        List<MongoHouse> mongoHouseList = this.mongoTemplate.find(query, MongoHouse.class);

        return mongoHouseList;
    }
}
