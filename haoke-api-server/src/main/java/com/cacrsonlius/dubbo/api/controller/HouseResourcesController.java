package com.cacrsonlius.dubbo.api.controller;

import com.cacrsonlius.dubbo.api.service.HouseResourcesService;
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
        }catch (Exception e) {
            e.printStackTrace();
        }


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping
    public ResponseEntity<String> get(){
        return ResponseEntity.ok("ok");
    }
}
