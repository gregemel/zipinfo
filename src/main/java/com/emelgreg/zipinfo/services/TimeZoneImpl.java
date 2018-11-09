package com.emelgreg.zipinfo.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TimeZoneImpl implements TimeZone {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String get(String latitude, String longitude) {
        String apiKey = "AIzaSyDV0QKbsTdAvxqJq5eQmiCkny-_vH_-yZg";
        return callEndpoint(latitude, longitude, apiKey);
    }

    private String callEndpoint(String latitude, String longitude, String apiKey) {
        long timeStamp = System.currentTimeMillis() / 1000;

        String uri = String.format("https://maps.googleapis.com/maps/api/timezone/json?location=%s,%s&timestamp=%s&key=%s",
                latitude, longitude, timeStamp, apiKey);

        String json = restTemplate.getForObject(uri, String.class);

        JSONObject obj = new JSONObject(json);
        return obj.getString("timeZoneName");
    }
}