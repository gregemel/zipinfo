package com.emelgreg.zipinfo.handlers;

import com.emelgreg.zipinfo.models.Location;

public interface OpenWeatherResponseHandler {
    Location parse(String json);
}
