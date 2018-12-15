package com.emelgreg.zipinfo.ports;

import com.emelgreg.zipinfo.models.LocationConditions;

public interface CurrentConditionsPort {
    LocationConditions get(String zip);
}
