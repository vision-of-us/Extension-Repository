package com.telerik.extension_repository.config;

import com.telerik.extension_repository.interceptors.IPInterceptor;
import com.telerik.extension_repository.interceptors.VisitedInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorsConfig extends WebMvcConfigurerAdapter {
    private final IPInterceptor ipInterceptor;
    private final VisitedInterceptor visitedInterceptor;

    @Autowired
    public InterceptorsConfig(IPInterceptor ipInterceptor, VisitedInterceptor visitedInterceptor) {
        this.ipInterceptor = ipInterceptor;
        this.visitedInterceptor = visitedInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.ipInterceptor).addPathPatterns("/");
        registry.addInterceptor(this.visitedInterceptor).addPathPatterns("/");
    }
}
