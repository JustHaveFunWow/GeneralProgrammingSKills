package com.knight.ucenter.server.config;

import com.knight.ucenter.server.interceptor.UcenterWebInterceptor;
import com.knight.upms.client.shiro.filters.KnightAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class InterceptorsConfig extends WebMvcConfigurerAdapter{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new UcenterWebInterceptor()).addPathPatterns("/*");
    }
}
