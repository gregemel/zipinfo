package com.emelgreg.zipinfo.controllers;

import com.emelgreg.zipinfo.ports.ZipWeatherHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/zipinfo")
public class ZipHttpController {

    @Autowired
    private ZipWeatherHandler handler;

    @GetMapping("/{zip}")
    public String get(@PathVariable("zip") String zip) {
        if(zip.length() < 5) {
            return "This service requires a valid zip code.";
        } else {
            return handler.handleWeatherRequest(zip).toString();
        }
    }
}