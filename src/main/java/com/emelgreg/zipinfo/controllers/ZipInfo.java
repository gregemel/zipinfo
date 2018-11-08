package com.emelgreg.zipinfo.controllers;

import com.emelgreg.zipinfo.services.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/zipinfo")
public class ZipInfo {

    @Autowired
    private Location locationService;

    @GetMapping("/{zip}")
    public String get(@PathVariable("zip") String zip) {
        return locationService.getLocationInfo(zip).toString();
    }
}