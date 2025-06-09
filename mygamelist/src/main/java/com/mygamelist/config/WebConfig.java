package com.mygamelist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Pode especificar também: "http://127.0.0.1:5500"
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
