package com.weather.weatherdemo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("Weather API")
                        .description("""
                                Приложение демонстрирует работу с Spring Boot
                                и получение текущей погоды по названию города
                                через внешний сервис WeatherAPI.
                                """)
                        .version("1.0.0")
        );
    }
}
