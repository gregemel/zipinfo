package com.emelgreg.zipinfo.services;

import com.emelgreg.zipinfo.models.CityTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TemperatureImpl implements Temperature {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OpenWeatherParser parser;

    @Value("${OpenWeatherKey}")
    private String apiKey;

    @Override
    public CityTemp get(String zip) {

        try {
            String results = callEndpoint(zip, apiKey);
            return parser.parse(results);
        } catch (Exception ex) {
            System.out.println("Failed to call OpenWeather endpoint: " + ex.getMessage());
            return new CityTemp("unknown", "unavailable", "unavailable", "unavailable");
        }
    }

    private String callEndpoint(String zipCode, String apiKey) {
        String uri = String.format("http://api.openweathermap.org/data/2.5/weather?zip=%s,us&appid=%s", zipCode, apiKey);

        return restTemplate.getForObject(uri, String.class);
    }
}