package com.emelgreg.zipinfo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/zipinfo")
public class ZipInfo {

    @GetMapping("/{zip}")
    public String get(@PathVariable("zip") String zip) {
        return "no zip info yet for " + zip;
    }
}
