package com.emelgreg.zipinfo.core;

import com.emelgreg.zipinfo.models.Location;
import com.emelgreg.zipinfo.models.Weather;
import com.emelgreg.zipinfo.ports.ElevationService;
import com.emelgreg.zipinfo.ports.TimeZoneService;
import com.emelgreg.zipinfo.ports.WeatherService;
import com.emelgreg.zipinfo.ports.ZipWeatherHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZipWeatherHandlerImpl implements ZipWeatherHandler {
    @Autowired
    private WeatherService temperatureService;

    @Autowired
    private TimeZoneService timeZoneService;

    @Autowired
    private ElevationService elevationService;

    @Override
    public Weather handleWeatherRequest(String zip) {

        Location location = temperatureService.get(zip);

        return new Weather(
                location.getCity(),
                location.getTemperature(),
                timeZoneService.get(location),
                elevationService.get(location));
    }
}