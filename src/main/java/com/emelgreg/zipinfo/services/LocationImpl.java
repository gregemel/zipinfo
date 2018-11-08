package com.emelgreg.zipinfo.services;

import com.emelgreg.zipinfo.models.LocationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationImpl implements Location {
    @Autowired
    private Temperature temperatureService;

    @Autowired
    private TimeZone timeZoneService;

    @Autowired
    private Elevation elevationService;

    @Override
    public LocationInfo getLocationInfo(String zip) {
        return new LocationInfo(temperatureService.get(zip), timeZoneService.get(zip), elevationService.get(zip));
    }
}