package com.emelgreg.zipinfo.adapters;

import com.emelgreg.zipinfo.ports.CurrentConditionsPort;
import com.emelgreg.zipinfo.models.LocationConditions;
import com.emelgreg.zipinfo.services.OpenWeatherResponseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherHttpClientAdapter implements CurrentConditionsPort {

    @Autowired
    public WeatherHttpClientAdapter(RestTemplate restTemplate, OpenWeatherResponseParser responseParser) {
        this.restTemplate = restTemplate;
        this.responseParser = responseParser;
    }

    private RestTemplate restTemplate;
    private OpenWeatherResponseParser responseParser;

    @Value("${OpenWeatherKey}")
    private String apiKey;

    @Override
    public LocationConditions get(String zip) {

        try {
            //todo: add time-based cache; probably set time-to-live = 1 hour
            String results = callEndpoint(zip, apiKey);
            return responseParser.parse(results);
        } catch (Exception ex) {
            System.out.println("Failed to call OpenWeather endpoint: " + ex.getMessage());
            return new LocationConditions("unknown", "unavailable", "unavailable", "unavailable");
        }
    }

    private String callEndpoint(String zipCode, String apiKey) {
        //todo: use uri builder; move url to applications properties, etc.
        String uri = String.format("http://api.openweathermap.org/data/2.5/weather?zip=%s,us&appid=%s", zipCode, apiKey);

        return restTemplate.getForObject(uri, String.class);
    }
}