package com.emelgreg.zipinfo.services;

import org.springframework.stereotype.Service;

@Service
public class TemperatureImpl implements Temperature {
    @Override
    public String get(String zip) {
        return "unavailable";
    }
}
