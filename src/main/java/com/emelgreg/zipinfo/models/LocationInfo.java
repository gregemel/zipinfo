package com.emelgreg.zipinfo.models;

public class LocationInfo {
    private String zip;
    private String temperature;
    private String timeZone;
    private String elevation;

    public LocationInfo(String temperature, String timeZone, String elevation) {
        this.temperature = temperature;
        this.timeZone = timeZone;
        this.elevation = elevation;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "LocationInfo{" +
                "zip='" + zip + '\'' +
                ", temperature='" + temperature + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", elevation='" + elevation + '\'' +
                '}';
    }
}
