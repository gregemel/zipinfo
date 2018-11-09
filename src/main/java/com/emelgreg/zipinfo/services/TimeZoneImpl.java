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

    String callEndpoint(String latitude, String longitude, String apiKey) {

        long timeStamp = System.currentTimeMillis() / 1000;

        //https://maps.googleapis.com/maps/api/timezone/json?location=45.52,-122.67&timestamp=1458000000&key=AIzaSyDV0Q
        String uri = String.format("https://maps.googleapis.com/maps/api/timezone/json?location=%s,%s&timestamp=%s&key=%s",
                latitude, longitude, timeStamp, apiKey);

        String json = restTemplate.getForObject(uri, String.class);

        JSONObject obj = new JSONObject(json);
        return obj.getString("timeZoneName");
    }
}



//https://maps.googleapis.com/maps/api/timezone/json?location=45.52,-122.67&timestamp=1458000000&key=AIzaSyDV0QKbsTdAvxqJq5eQmiCkny-_vH_-yZg

//AIzaSyDV0QKbsTdAvxqJq5eQmiCkny-_vH_-yZg

// AIzaSyBcAMZ9IGln_giqXBzeNGISSWAspA9JkzY

// https://maps.googleapis.com/maps/api/timezone/json?location=38.908133,-77.047119&timestamp=1458000000&key=AIzaSyBcAMZ9IGln_giqXBzeNGISSWAspA9JkzY