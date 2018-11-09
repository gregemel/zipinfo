package com.emelgreg.zipinfo.services;

import com.emelgreg.zipinfo.models.CityTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TemperatureImpl implements Temperature {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OpenWeatherParser parser;

    @Override
    public CityTemp get(String zip) {

        String results = callEndpoint(zip, "ba89902eb83ba51bf619f24e6fcb8935");

        return parser.parse(results);
    }

    String callEndpoint(String zipCode, String apiKey) {
        //"http://api.openweathermap.org/data/2.5/weather?zip=97201,us&appid=ba89902eb83ba51bf619f24e6fcb8935";
        String uri = String.format("http://api.openweathermap.org/data/2.5/weather?zip=%s,us&appid=%s", zipCode, apiKey);

        return restTemplate.getForObject(uri, String.class);
    }
}