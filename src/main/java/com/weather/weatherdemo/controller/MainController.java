package com.weather.weatherdemo.controller;

import com.weather.weatherdemo.dto.WeatherResponse;
import com.weather.weatherdemo.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping
@Tag(name = "Weather", description = "Контроллер для получения текущей погоды")
public class MainController {

    private final WeatherService weatherService;

    @Operation(
            summary = "Главная страница с погодой",
            description = "Возвращает страницу с текущей погодой для указанного города"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Погода успешно получена",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = WeatherResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка при получении данных о погоде",
                    content = @Content
            )
    })
    @GetMapping("/")
    public String mainPage(
            @Parameter(
                    description = "Название города",
                    example = "Moscow"
            )
            @RequestParam(defaultValue = "Moscow") String city,
            Model model
    ) {
        try {
            WeatherResponse weather = weatherService.getCurrentWeather(city);
            model.addAttribute("weather", weather);
            model.addAttribute("city", city);
        } catch (Exception e) {
            model.addAttribute("weather", null);
            model.addAttribute("city", city);
            model.addAttribute("error",
                    "Не удалось получить данные о погоде для города: " + city);
        }
        return "main";
    }
}
