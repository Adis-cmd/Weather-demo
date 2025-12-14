package com.weather.weatherdemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Описание погодных условий")
public class Condition {

    @Schema(description = "Текстовое описание погоды", example = "Partly cloudy")
    private String text;

    @Schema(
            description = "URL иконки погоды",
            example = "//cdn.weatherapi.com/weather/64x64/day/116.png"
    )
    private String icon;
}
