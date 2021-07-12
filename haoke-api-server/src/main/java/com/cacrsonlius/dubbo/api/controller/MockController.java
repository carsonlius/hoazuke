package com.cacrsonlius.dubbo.api.controller;

import com.cacrsonlius.dubbo.api.config.MocConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RequestMapping("mock")
@RestController
@CrossOrigin
public class MockController {
    @Autowired
    private MocConfig mocConfig;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("indexMenu")
    public Object indexMenu() throws IOException {
        String indexMenu = mocConfig.getIndexMenu();
        return objectMapper.readValue(indexMenu,objectMapper.getTypeFactory().constructType(HashMap.class));
    }

    @GetMapping("indexFaq")
    public Object indexFaq(){
        return mocConfig.getIndexFaq();
    }

    @GetMapping("indexHouse")
    public Object indexHouse() throws IOException {
        return objectMapper.readValue(mocConfig.getIndexHouse(),objectMapper.getTypeFactory().constructType(HashMap.class));
    }

    @GetMapping("indexInfo")
    public Object indexInfo(){
        return mocConfig.getIndexInfo();
    }

    @GetMapping("infosList1")
    public Object infosList1(){
        return mocConfig.getInfosList1();
    }

    @GetMapping("infosList2")
    public Object infosList2(){
        return mocConfig.getInfosList2();
    }


    @GetMapping("infosList3")
    public Object infosList3(){
        return mocConfig.getInfosList3();
    }

    @GetMapping("my")
    public Object my(){
        return mocConfig.getMy();
    }
}
