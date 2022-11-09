package com.sangeng.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 配置跨域
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 设置允许cookie
                .allowCredentials(true)
                // 设置允许跨域的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 设置允许跨域的请求头header属性
                .allowedHeaders("*")
                // 设置允许跨域的时间
                .maxAge(3600);
    }
}
