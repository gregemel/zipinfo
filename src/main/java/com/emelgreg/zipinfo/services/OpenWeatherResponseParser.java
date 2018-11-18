package com.emelgreg.zipinfo.services;

import com.emelgreg.zipinfo.models.Location;

public interface OpenWeatherResponseParser {
    Location parse(String json);
}
