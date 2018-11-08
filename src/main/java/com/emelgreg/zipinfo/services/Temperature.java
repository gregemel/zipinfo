package com.emelgreg.zipinfo.services;

import com.emelgreg.zipinfo.models.CityTemp;

public interface Temperature {
    CityTemp get(String zip);
}
