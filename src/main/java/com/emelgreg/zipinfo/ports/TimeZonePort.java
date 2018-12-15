package com.emelgreg.zipinfo.ports;

import com.emelgreg.zipinfo.models.LocationConditions;

public interface TimeZonePort {
    String get(LocationConditions locationConditions);
}
