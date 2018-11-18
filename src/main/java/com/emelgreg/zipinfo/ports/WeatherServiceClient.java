package com.emelgreg.zipinfo.ports;

import com.emelgreg.zipinfo.models.Location;

public interface WeatherServiceClient {
    Location get(String zip);
}
