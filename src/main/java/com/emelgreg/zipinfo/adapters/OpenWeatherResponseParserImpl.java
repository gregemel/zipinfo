package com.emelgreg.zipinfo.adapters;

import com.emelgreg.zipinfo.models.Location;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import static java.lang.Math.round;

@Service
public class OpenWeatherResponseParserImpl implements OpenWeatherResponseParser {

    public Location parse(String json) throws JSONException {

        JSONObject obj = new JSONObject(json);
        String city = obj.getString("name");

        JSONObject main = obj.getJSONObject("main");
        double kelvin = main.getDouble("temp");
        String temp = convertKelvinToFahrenheitString(kelvin);

        JSONObject coord = obj.getJSONObject("coord");
        double longitude = coord.getDouble("lon");
        double latitude = coord.getDouble("lat");

        return new Location(city, temp, Double.toString(latitude), Double.toString(longitude));
    }

    private String convertKelvinToFahrenheitString(double kelvin) {
        return Long.toString(round(kelvin - 273.15) * 9/5 + 32) + "F";
    }
}
