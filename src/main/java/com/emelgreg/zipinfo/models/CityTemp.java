package com.emelgreg.zipinfo.models;

public class CityTemp {

    private String name;
    private String temp;

    public CityTemp(String name, String temp) {

        this.name = name;
        this.temp = temp;
    }

    public String getCity() {
        return this.name;
    }

    public String getTemperature() {
        return this.temp;
    }
}
