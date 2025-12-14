package com.weather.weatherdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Ответ с данными о погоде")
public class WeatherResponse {

    @Schema(description = "Информация о локации")
    private Location location;

    @Schema(description = "Текущие погодные условия")
    private Current current;
}
