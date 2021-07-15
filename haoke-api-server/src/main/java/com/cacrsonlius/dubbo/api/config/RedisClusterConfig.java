package com.cacrsonlius.dubbo.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisClusterConfig {
    @Autowired
    private ClusterConfigurationProperties clusterConfigurationProperties;

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(clusterConfigurationProperties.getNodes());
        clusterConfiguration.setMaxRedirects(clusterConfigurationProperties.getMaxRedirects());
//        clusterConfiguration.setClusterNodes(clusterConfigurationProperties.getNodes());
        return new JedisConnectionFactory(clusterConfiguration);
    }

    @Bean
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
