package com.emelgreg.zipinfo.ports;

import com.emelgreg.zipinfo.models.Location;

public interface ElevationServiceClient {
    String get(Location location);
}