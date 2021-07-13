package com.cacrsonlius.dubbo.api.controller;

import com.cacrsonlius.dubbo.api.service.HouseResourcesService;
import com.cacrsonlius.dubbo.api.vo.TableResult;
import com.carsonlius.dubbo.server.popj.HouseResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("house/resources")
public class HouseResourcesController {
    @Autowired
    private HouseResourcesService houseResourcesService;

    @PostMapping
    public ResponseEntity save(@RequestBody HouseResources houseResources) {
        try {
            boolean save = houseResourcesService.save(houseResources);
            if (save) {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @GetMapping
    public ResponseEntity<TableResult<HouseResources>> list(HouseResources houseResources,
                                                            @RequestParam(name = "currentPage", defaultValue = "1") Integer current,
                                                            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        return ResponseEntity.ok(this.houseResourcesService.queryList(houseResources, current, pageSize));
    }

    @GetMapping("/id")
    public ResponseEntity<HouseResources> queryHouseResourceById(@RequestParam(name = "id") Long id){
        HouseResources houseResources = houseResourcesService.queryHouseResourceById(id);
        return ResponseEntity.ok(houseResources);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody HouseResources houseResources){
        boolean bool = houseResourcesService.updateHouseResources(houseResources);
        if (bool) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
