package com.emelgreg.zipinfo.services;

import org.springframework.stereotype.Service;

@Service
public class TimeZoneImpl implements TimeZone {
    @Override
    public String get(String zip) {
        return "unavailable";
    }
}
