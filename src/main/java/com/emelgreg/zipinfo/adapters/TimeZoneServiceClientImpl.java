package com.emelgreg.zipinfo.adapters;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class TimeZoneServiceClientImpl implements TimeZoneServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${GoogleKey}")
    private String apiKey;

    @Override
    public String get(String latitude, String longitude) {
        try {
            return callEndpoint(latitude, longitude, apiKey);
        } catch (Exception ex) {
            System.out.println("Failed to call TimeZoneServiceClient endpoint: " + ex.getMessage());
            return "unavailable";
        }
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