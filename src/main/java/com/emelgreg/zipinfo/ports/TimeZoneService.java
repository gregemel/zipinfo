package com.emelgreg.zipinfo.ports;

import com.emelgreg.zipinfo.models.Location;

public interface TimeZoneService {
    String get(Location location);
}