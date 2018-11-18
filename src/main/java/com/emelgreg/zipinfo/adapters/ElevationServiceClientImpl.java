package com.emelgreg.zipinfo.adapters;

import com.emelgreg.zipinfo.models.Location;
import com.emelgreg.zipinfo.ports.ElevationServiceClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.lang.Math.round;

@Service
public class ElevationServiceClientImpl implements ElevationServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${GoogleKey}")
    private String apiKey;

    @Override
    public String get(Location location) {
        try {
            return callEndpoint(location.getLatitude(), location.getLongitude(), apiKey);
        } catch (Exception ex) {
            System.out.println("Failed to call ElevationServiceClient endpoint: " + ex.getMessage());
            return "unavailable";
        }
    }

    private String callEndpoint(String latitude, String longitude, String apiKey) {

        String uri = String.format("https://maps.googleapis.com/maps/api/elevation/json?locations=%s,%s&key=%s",
                latitude, longitude, apiKey);

        String json = restTemplate.getForObject(uri, String.class);

        JSONObject obj = new JSONObject(json);
        JSONArray arr = obj.getJSONArray("results");
        double elevation = round(arr.getJSONObject(0).getDouble("elevation") * 3.281);
        return Double.toString(elevation) + "ft";
    }
}