package com.weather.weatherdemo.service.impl;


import com.weather.weatherdemo.dto.WeatherResponse;
import com.weather.weatherdemo.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public WeatherResponse getCurrentWeather(String city) {
        String url = apiUrl
                + "?key=" + apiKey
                + "&q=" + city
                + "&aqi=no";
        return restTemplate.getForObject(url, WeatherResponse.class);
    }

}
