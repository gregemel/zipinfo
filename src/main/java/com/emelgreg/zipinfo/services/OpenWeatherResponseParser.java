package com.emelgreg.zipinfo.services;

import com.emelgreg.zipinfo.models.LocationConditions;

public interface OpenWeatherResponseParser {
    LocationConditions parse(String json);
}
