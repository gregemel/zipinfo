package com.emelgreg.zipinfo.adapters;

import com.emelgreg.zipinfo.models.Location;

public interface WeatherServiceClient {
    Location get(String zip);
}
