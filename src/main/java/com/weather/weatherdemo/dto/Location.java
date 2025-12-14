package com.weather.weatherdemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Информация о городе")
public class Location {

    @Schema(description = "Название города", example = "Moscow")
    private String name;

    @Schema(description = "Страна", example = "Russia")
    private String country;
}
