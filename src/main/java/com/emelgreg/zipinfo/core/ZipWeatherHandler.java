package com.emelgreg.zipinfo.core;

import com.emelgreg.zipinfo.models.LocationConditions;
import com.emelgreg.zipinfo.models.ZipWeather;
import com.emelgreg.zipinfo.ports.ElevationPort;
import com.emelgreg.zipinfo.ports.TimeZonePort;
import com.emelgreg.zipinfo.ports.CurrentConditionsPort;
import com.emelgreg.zipinfo.ports.ZipWeatherPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZipWeatherHandler implements ZipWeatherPort {

    @Autowired
    public ZipWeatherHandler(CurrentConditionsPort currentConditions,
                             TimeZonePort timeZone,
                             ElevationPort elevation) {
        this.currentConditions = currentConditions;
        this.timeZone = timeZone;
        this.elevation = elevation;
    }

    private CurrentConditionsPort currentConditions;
    private TimeZonePort timeZone;
    private ElevationPort elevation;

    @Override
    public ZipWeather getWeatherByZip(String zip) {

        LocationConditions locationConditions = currentConditions.get(zip);

        return new ZipWeather(
                locationConditions.getCity(),
                locationConditions.getTemperature(),
                timeZone.get(locationConditions),
                elevation.get(locationConditions));
    }
}