package com.cacrsonlius.dubbo.api.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import sun.nio.cs.UTF_8;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RedisCacheInterceptor implements HandlerInterceptor {

    @Autowired
    public RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String method = request.getMethod();
        if (!StringUtils.equalsAnyIgnoreCase(method, "GET")) {
            return true;
        }

        // todo 生成key
        String key = createRedisKey(request);

        // 获取缓存
        String data = redisTemplate.opsForValue().get(key);

        // 没有命中
        if (StringUtils.isEmpty(data)) {
            return true;
        }

        // 返回请求
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(data);
        return false;
    }

    public static String createRedisKey(HttpServletRequest request){
        
        return "";
    }
}
