package com.itheima.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* 浏览器能够访问本地文件
* */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${ImagesPath}")
    private String ImagesPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations(ImagesPath);
    }
}
