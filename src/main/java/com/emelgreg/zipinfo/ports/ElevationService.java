package com.emelgreg.zipinfo.ports;

import com.emelgreg.zipinfo.models.Location;

public interface ElevationService {
    String get(Location location);
}