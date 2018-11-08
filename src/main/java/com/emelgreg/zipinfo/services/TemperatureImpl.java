package com.emelgreg.zipinfo.services;

import com.emelgreg.zipinfo.models.CityTemp;
import org.springframework.stereotype.Service;

@Service
public class TemperatureImpl implements Temperature {
    @Override
    public CityTemp get(String zip) {
        return new CityTemp("unknown", "unavailable");
    }
}