package com.cacrsonlius.dubbo.api.controller;

import com.cacrsonlius.dubbo.api.service.AdService;
import com.cacrsonlius.dubbo.api.vo.WebResult;
import com.carsonlius.dubbo.server.popj.Ad;
import com.carsonlius.dubbo.server.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ad")
@CrossOrigin
public class AdController {

    @Autowired
    private AdService adService;


    @GetMapping
    public WebResult queryIndexAd(@RequestParam(name = "type", defaultValue = "1") Integer type, @RequestParam(name = "page", defaultValue = "1") Integer page,
                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
        PageInfo<Ad> pageInfo = adService.queryAdList(type, page, pageSize);

        List<Map<String,String>> originalList = new ArrayList<>();
        for (Ad record : pageInfo.getRecords()) {
            Map<String, String> map = new HashMap<>();
            map.put("original", record.getUrl());
            originalList.add(map);
        }

        return WebResult.ok(originalList);
    }
}
