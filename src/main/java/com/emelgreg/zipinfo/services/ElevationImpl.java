package com.emelgreg.zipinfo.services;

import org.springframework.stereotype.Service;

@Service
public class ElevationImpl implements Elevation {
    @Override
    public String get(String zip) {
        return "unavailable";
    }
}
