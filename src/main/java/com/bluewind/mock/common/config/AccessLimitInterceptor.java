package com.bluewind.mock.common.config;

import com.bluewind.mock.common.annotation.AccessLimit;
import com.bluewind.mock.common.base.Result;
import com.bluewind.mock.common.util.IpAddressUtils;
import com.bluewind.mock.common.util.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @author liuxingyu01
 * @date 2022-03-13-13:48
 **/
@Component
public class AccessLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            // 判断请求类型，如果是OPTIONS，直接返回
            String options = HttpMethod.OPTIONS.toString();
            if (options.equals(request.getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);
                return true;
            }

            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
            // 接口上没有注解，说明这个接口不做限制
            if (accessLimit == null) {
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            String ip = IpAddressUtils.getIpAddress(request);
            String method = request.getMethod();
            String requestURI = request.getRequestURI().replace("/", "");

            String redisKey = ip + ":" + method + ":" + requestURI;
            Object redisResult = redisTemplate.opsForValue().get(redisKey);
            // 获取当前访问次数
            Integer count = JacksonUtils.convertValue(redisResult, Integer.class);
            if (count == null) {
                // 在规定周期内第一次访问，存入redis，次数+1
                redisTemplate.opsForValue().increment(redisKey, 1);
                redisTemplate.expire(redisKey, seconds, TimeUnit.SECONDS);
            } else {
                if (count >= maxCount) {
                    // 超出访问限制次数
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    Result result = Result.create(403, accessLimit.msg());
                    out.write(JacksonUtils.writeValueAsString(result));
                    out.flush();
                    out.close();
                    return false;
                } else {
                    // 没超出访问限制次数，则继续次数+1
                    redisTemplate.opsForValue().increment(redisKey, 1);
                }
            }
        }
        return true;
    }
}