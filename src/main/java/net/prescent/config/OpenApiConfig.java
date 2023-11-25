package net.prescent.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("API Document")
                .version("v0.0.1")
                .description("데모 프로젝트의 API 명세서");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}