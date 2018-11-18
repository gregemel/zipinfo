package com.emelgreg.zipinfo.handlers;

import com.emelgreg.zipinfo.models.Location;
import com.emelgreg.zipinfo.models.Weather;
import com.emelgreg.zipinfo.adapters.ElevationServiceClient;
import com.emelgreg.zipinfo.adapters.WeatherServiceClient;
import com.emelgreg.zipinfo.adapters.TimeZoneServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZipWeatherHandlerImpl implements ZipWeatherHandler {
    @Autowired
    private WeatherServiceClient temperatureService;

    @Autowired
    private TimeZoneServiceClient timeZoneService;

    @Autowired
    private ElevationServiceClient elevationService;

    @Override
    public Weather handleWeatherRequest(String zip) {

        Location location = temperatureService.get(zip);

        return new Weather(location.getCity(), location.getTemperature(),
                timeZoneService.get(location.getLatitude(), location.getLongitude()),
                elevationService.get(location.getLatitude(), location.getLongitude()));
    }
}