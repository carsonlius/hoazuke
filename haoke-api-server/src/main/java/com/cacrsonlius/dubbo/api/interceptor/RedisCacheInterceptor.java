package com.cacrsonlius.dubbo.api.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class RedisCacheInterceptor implements HandlerInterceptor {

    @Autowired
    public RedisTemplate<String,String> redisTemplate;

    private  static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String method = request.getMethod();
        if (!StringUtils.equalsAnyIgnoreCase(method, "GET")) {
            return true;
        }

        // 生成key
        String key = createRedisKey(request);

        // 获取缓存
        String data = redisTemplate.opsForValue().get(key);

        // 没有命中
        if (StringUtils.isEmpty(data)) {
            System.out.println("没有命中缓存");
            return true;
        }

        // 返回请求
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        // 支持跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Method", "GET,POST,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Token");
        response.getWriter().write(data);
        System.out.println("命中缓存数据" + data);
        return false;
    }

    public static String createRedisKey(HttpServletRequest request) throws IOException {
        String paramsStr = request.getRequestURI();
        Map<String,String[]> parameter = request.getParameterMap();
        if (parameter.isEmpty()) {
            paramsStr += IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
        } else {
            paramsStr += objectMapper.writeValueAsString(parameter);
        }


        return "WEB_DATA_" + DigestUtils.md5DigestAsHex(paramsStr.getBytes(StandardCharsets.UTF_8));
    }
}
