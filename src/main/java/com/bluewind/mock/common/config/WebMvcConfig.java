package com.bluewind.mock.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxingyu01
 * @date 2022-03-13-13:47
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    AccessLimitInterceptor accessLimitInterceptor;

    @Autowired
    AuthenticeInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathList = new ArrayList<>();
        excludePathList.add("/login");
        excludePathList.add("/doLogin");
        excludePathList.add("/register");
        excludePathList.add("/doRegister");
        excludePathList.add("/api/**"); // 开发api上下文，作为mock公共上下文

        // 开放静态文件
        excludePathList.add("/static/**");//静态资源不拦截
        excludePathList.add("/css/**");
        excludePathList.add("/images/**");
        excludePathList.add("/js/**");
        excludePathList.add("/lib/**");

        // 注册会话拦截器
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePathList)
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html", "/doc.html", "/service-worker.js");// 配置swagger-ui不被拦截

        // 注册限流拦截器
        registry.addInterceptor(accessLimitInterceptor)
                .addPathPatterns("/**");
    }


    /**
     * 配置静态资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置静态资源不被拦截
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        // 配置swagger-ui不被拦截(knife4j)
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
