package com.emelgreg.zipinfo.services;

import com.emelgreg.zipinfo.models.LocationInfo;

public interface Location {
    LocationInfo getLocationInfo(String zip);
}
