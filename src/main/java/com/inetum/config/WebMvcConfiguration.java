package com.inetum.config;

import com.sun.istack.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                registry.addMapping("/*")
                        .allowedMethods("GET", "POST")
                        .allowedHeaders("*")
                        .allowedOrigins("*");

                registry.addMapping("/category/*/**")
                        .allowedMethods("GET", "POST", "PUT","DELETE")
                        .allowedHeaders("*")
                        .allowedOrigins("*")
                        .maxAge(3600);

                registry.addMapping("/product/*/**")
                        .allowedMethods("GET", "POST", "PUT","DELETE")
                        .allowedHeaders("*")
                        .allowedOrigins("*")
                        .maxAge(3600);

            }

            @Override
            public void addResourceHandlers(@NotNull ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**")
                        .addResourceLocations("classpath:/static/");
            }
        };
    }
}