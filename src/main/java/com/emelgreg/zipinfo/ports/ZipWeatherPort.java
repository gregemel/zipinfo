package com.emelgreg.zipinfo.ports;

import com.emelgreg.zipinfo.models.ZipWeather;

public interface ZipWeatherPort {
    ZipWeather getWeatherByZip(String zip);
}
