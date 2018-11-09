package com.emelgreg.zipinfo.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.lang.Math.round;

@Service
public class ElevationImpl implements Elevation {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public String get(String latitude, String longitude) {
        String apiKey = "AIzaSyDV0QKbsTdAvxqJq5eQmiCkny-_vH_-yZg";
        return callEndpoint(latitude, longitude, apiKey);
    }

    String callEndpoint(String latitude, String longitude, String apiKey) {

        //https://maps.googleapis.com/maps/api/elevation/json?locations=39.7391536,-104.9847034&key=AIzaSyDV0
        String uri = String.format("https://maps.googleapis.com/maps/api/elevation/json?locations=%s,%s&key=%s",
                latitude, longitude, apiKey);

        String json = restTemplate.getForObject(uri, String.class);

        //3.281 ft per meter

        JSONObject obj = new JSONObject(json);
        JSONArray arr = obj.getJSONArray("results");
        double elevation = round(arr.getJSONObject(0).getDouble("elevation") * 3.281);
        return Double.toString(elevation) + "ft";
    }
}


//https://maps.googleapis.com/maps/api/elevation/json?locations=39.7391536,-104.9847034&key=AIzaSyDV0QKbsTdAvxqJq5eQmiCkny-_vH_-yZg