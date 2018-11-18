package com.emelgreg.zipinfo.services;

import com.emelgreg.zipinfo.models.Location;
import com.emelgreg.zipinfo.services.OpenWeatherResponseParserImpl;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherResponseParserSpec {

    private OpenWeatherResponseParserImpl target = new OpenWeatherResponseParserImpl();

    @Test
    public void shouldParseJsonResults() throws JSONException {

        String json = "{\"coord\":{\"lon\":-122.67,\"lat\":45.52},\"weather\":[{\"id\":701,\"main\":\"Mist\",\"description\":\"mist\",\"icon\":\"50n\"}],\"base\":\"stations\",\"main\":{\"temp\":277.33,\"pressure\":1031,\"humidity\":92,\"temp_min\":275.15,\"temp_max\":278.75},\"visibility\":14484,\"wind\":{\"speed\":0.96,\"deg\":34.5018},\"clouds\":{\"all\":1},\"dt\":1541652960,\"sys\":{\"type\":1,\"id\":2963,\"message\":0.0058,\"country\":\"US\",\"sunrise\":1541689229,\"sunset\":1541724468},\"id\":420029235,\"name\":\"Portland\",\"cod\":200}";

        Location location = target.parse(json);

        assert(location != null);

        assert(location.getCity().equals("Portland"));
        assert(location.getTemperature().equals("39F"));
        assert(location.getLongitude().equals("-122.67"));
        assert(location.getLatitude().equals("45.52"));
    }
}