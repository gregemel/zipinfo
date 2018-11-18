package com.emelgreg.zipinfo.ports;

import com.emelgreg.zipinfo.models.Weather;

public interface ZipWeatherHandler {
    Weather handleWeatherRequest(String zip);
}
