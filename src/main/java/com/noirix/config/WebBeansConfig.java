package com.noirix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class WebBeansConfig {

    @Bean
    public ViewResolver viewResolver(){
        return new InternalResourceViewResolver("/WEB-INF/jsp/", "jsp");
    }
}
