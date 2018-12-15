package com.emelgreg.zipinfo.ports;

import com.emelgreg.zipinfo.models.LocationConditions;

public interface ElevationPort {
    String get(LocationConditions locationConditions);
}