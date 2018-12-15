package com.emelgreg.zipinfo.models;

public class ZipWeather {
    private String city;
    private String temperature;
    private String timeZone;
    private String elevation;

    public ZipWeather(String city, String temperature, String timeZone, String elevation) {
        this.city = city;
        this.temperature = temperature;
        this.timeZone = timeZone;
        this.elevation = elevation;
    }

    public String getCity() {
        return city;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getElevation() {
        return elevation;
    }

    @Override
    public String toString() {
        return "At the location " + city + ", " +
                "the temperature is " + temperature + ", " +
                "the timezone is " + timeZone + ", " +
                "and the elevation is " + elevation + ".";
    }
}
