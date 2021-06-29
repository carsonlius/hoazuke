package com.carsonlius.dubbo.server.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.carsonlius.dubbo.server.mapper")
@Configuration
public class MybatisConfig {


}
