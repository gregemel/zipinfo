package com.emelgreg.zipinfo.services;

import com.emelgreg.zipinfo.models.CityTemp;

public interface OpenWeatherParser {
    CityTemp parse(String json);
}
