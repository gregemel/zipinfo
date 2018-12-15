package com.emelgreg.zipinfo.controllers;

import com.emelgreg.zipinfo.ports.ZipWeatherPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/api/v1/zipinfo")
public class ZipWeatherHttpController {

    @Autowired
    ZipWeatherHttpController(ZipWeatherPort handler) {
        this.handler = handler;
    }

    private ZipWeatherPort handler;
    private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @GetMapping("/{zip}")
    public String get(@PathVariable("zip") String zip) {
        if(zip.length() < 5) {
            String msg = String.format("This service requires a valid zip code.  '%s' is not valid", zip);
            log.error(msg);
            return msg;
        } else {
            return handler.getWeatherByZip(zip).toString();
        }
    }
}