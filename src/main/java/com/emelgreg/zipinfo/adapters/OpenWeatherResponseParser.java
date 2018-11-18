package com.emelgreg.zipinfo.adapters;

import com.emelgreg.zipinfo.models.Location;

public interface OpenWeatherResponseParser {
    Location parse(String json);
}
