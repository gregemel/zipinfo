package com.emelgreg.zipinfo.adapters;

import com.emelgreg.zipinfo.models.LocationConditions;
import com.emelgreg.zipinfo.ports.ElevationPort;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.lang.Math.round;

@Service
public class ElevationHttpClientAdapter implements ElevationPort {

    @Autowired
    public ElevationHttpClientAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private RestTemplate restTemplate;

    @Value("${GoogleKey}")
    private String apiKey;

    @Override
    public String get(LocationConditions locationConditions) {
        try {
            //todo: check cache before calling endpoint
            return callEndpoint(locationConditions.getLatitude(), locationConditions.getLongitude(), apiKey);
        } catch (Exception ex) {
            System.out.println("Failed to call ElevationPort endpoint: " + ex.getMessage());
            return "unavailable";
        }
    }

    private String callEndpoint(String latitude, String longitude, String apiKey) {

        //todo: move entire uri to application.properties; consider RestTemplate uri-string interpolation

        //uri components builder
        //@Value("${ElevationUrl}");
        //String url = "http://example.com/search";
        //MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        //params.add("locations", String.format("%s,%s",latitude, longitude));
        //params.add("key", apiKey);
        //
        //UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build();
        //String uri = uriComponents.toUri();

        String uri = String.format("https://maps.googleapis.com/maps/api/elevation/json?locations=%s,%s&key=%s",
                latitude, longitude, apiKey);

        String json = restTemplate.getForObject(uri, String.class);

        JSONObject obj = new JSONObject(json);
        JSONArray arr = obj.getJSONArray("results");
        double elevationMeters = arr.getJSONObject(0).getDouble("elevation");
        double elevationFeet = round(elevationMeters * 3.281);
        return Double.toString(elevationFeet) + "ft";
    }
}