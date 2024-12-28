package com.famjam.famjam.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI familyApiDoc() {
        return new OpenAPI()
                .info(new Info()
                        .title("Family Connection & Matrimony API")
                        .description("API documentation for Family Connection & Matrimony application")
                        .version("1.0.0"));
    }
}