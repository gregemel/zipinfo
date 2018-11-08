package com.emelgreg.zipinfo.services;

import com.emelgreg.zipinfo.models.CityTemp;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherParserSpec {

    private OpenWeatherParserImpl target = new OpenWeatherParserImpl();

    @Test
    public void shouldParseJsonResults() throws JSONException {

        String json = "{\"coord\":{\"lon\":-122.67,\"lat\":45.52},\"weather\":[{\"id\":701,\"main\":\"Mist\",\"description\":\"mist\",\"icon\":\"50n\"}],\"base\":\"stations\",\"main\":{\"temp\":277.33,\"pressure\":1031,\"humidity\":92,\"temp_min\":275.15,\"temp_max\":278.75},\"visibility\":14484,\"wind\":{\"speed\":0.96,\"deg\":34.5018},\"clouds\":{\"all\":1},\"dt\":1541652960,\"sys\":{\"type\":1,\"id\":2963,\"message\":0.0058,\"country\":\"US\",\"sunrise\":1541689229,\"sunset\":1541724468},\"id\":420029235,\"name\":\"Portland\",\"cod\":200}";

        CityTemp cityTemp = target.parse(json);

        assert(cityTemp != null);

        assert(cityTemp.getCity().equals("Portland"));
        assert(cityTemp.getTemperature().equals("39F"));
    }

}
