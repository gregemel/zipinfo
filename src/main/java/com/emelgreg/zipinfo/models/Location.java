package com.emelgreg.zipinfo.models;

public class Location {

    private String name;
    private String temp;
    private String latitude;
    private String longitude;

    public Location(String name, String temp, String latitude, String longitude) {

        this.name = name;
        this.temp = temp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCity() {
        return this.name;
    }

    public String getTemperature() {
        return this.temp;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }
}
