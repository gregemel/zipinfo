package com.emelgreg.zipinfo.handlers;

import com.emelgreg.zipinfo.models.Weather;

public interface ZipWeatherHandler {
    Weather handleWeatherRequest(String zip);
}
