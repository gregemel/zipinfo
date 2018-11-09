package com.emelgreg.zipinfo.services;

import com.emelgreg.zipinfo.models.CityTemp;
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
        CityTemp cityTemp = temperatureService.get(zip);
        return new LocationInfo(cityTemp.getCity(), cityTemp.getTemperature(),
                timeZoneService.get(cityTemp.getLatitude(), cityTemp.getLongitude()),
                elevationService.get(cityTemp.getLatitude(), cityTemp.getLongitude()));
    }
}