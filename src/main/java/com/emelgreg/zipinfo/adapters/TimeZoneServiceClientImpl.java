package com.emelgreg.zipinfo.adapters;

import com.emelgreg.zipinfo.models.Location;
import com.emelgreg.zipinfo.ports.TimeZoneService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class TimeZoneServiceClientImpl implements TimeZoneService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${GoogleKey}")
    private String apiKey;

    @Override
    public String get(Location location) {
        try {
            return callEndpoint(location.getLatitude(), location.getLongitude(), apiKey);
        } catch (Exception ex) {
            System.out.println("Failed to call TimeZoneService endpoint: " + ex.getMessage());
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