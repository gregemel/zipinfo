package com.emelgreg.zipinfo.adapters;

import com.emelgreg.zipinfo.models.Location;

public interface TemperatureServiceClient {
    Location get(String zip);
}
