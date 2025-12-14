package com.weather.weatherdemo.service;

import com.weather.weatherdemo.dto.WeatherResponse;

public interface WeatherService {
    WeatherResponse getCurrentWeather(String city);
}
