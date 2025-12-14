package com.weather.weatherdemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Текущая погода")
public class Current {

    @Schema(description = "Температура в градусах Цельсия", example = "12.5")
    private double temp_c;

    @Schema(description = "Описание погодных условий")
    private Condition condition;
}
