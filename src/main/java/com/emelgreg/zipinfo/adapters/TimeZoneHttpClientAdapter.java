package com.emelgreg.zipinfo.adapters;

import com.emelgreg.zipinfo.models.LocationConditions;
import com.emelgreg.zipinfo.ports.TimeZonePort;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class TimeZoneHttpClientAdapter implements TimeZonePort {

    @Autowired
    public TimeZoneHttpClientAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private RestTemplate restTemplate;

    @Value("${GoogleKey}")
    private String apiKey;

    @Override
    public String get(LocationConditions locationConditions) {
        try {
            //todo: add time-based cache that is sensitive to daylight savings changes
            return callEndpoint(locationConditions.getLatitude(), locationConditions.getLongitude(), apiKey);
        } catch (Exception ex) {
            System.out.println("Failed to call TimeZonePort endpoint: " + ex.getMessage());
            return "unavailable";
        }
    }

    private String callEndpoint(String latitude, String longitude, String apiKey) {
        long timeStamp = System.currentTimeMillis() / 1000;

        //todo: use uri builder, pull url from application.properties, etc.
        String uri = String.format("https://maps.googleapis.com/maps/api/timezone/json?location=%s,%s&timestamp=%s&key=%s",
                latitude, longitude, timeStamp, apiKey);

        String json = restTemplate.getForObject(uri, String.class);

        JSONObject obj = new JSONObject(json);
        return obj.getString("timeZoneName");
    }
}