package com.github.hotire.spring.swagger;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(searchHandlerMethodArgumentResolver());
    }

    @Bean
    public HandlerMethodArgumentResolver searchHandlerMethodArgumentResolver() {
        return new SearchHandlerMethodArgumentResolver();
    }
}
