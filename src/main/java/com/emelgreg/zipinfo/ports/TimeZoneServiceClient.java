package com.emelgreg.zipinfo.ports;

import com.emelgreg.zipinfo.models.Location;

public interface TimeZoneServiceClient {
    String get(Location location);
}
