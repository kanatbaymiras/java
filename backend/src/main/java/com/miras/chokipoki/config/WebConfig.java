package com.miras.chokipoki.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow all endpoints under '/api/**' to accept requests from your frontend
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173")  // Allow your Vue.js frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Specify allowed HTTP methods
                .allowCredentials(true);  // Optional: Allows cookies or authentication to be sent
    }
}