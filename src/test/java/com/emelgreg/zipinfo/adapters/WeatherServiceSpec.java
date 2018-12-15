package com.emelgreg.zipinfo.adapters;

import com.emelgreg.zipinfo.models.LocationConditions;
import com.emelgreg.zipinfo.services.OpenWeatherResponseParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceSpec {

    private String zipCode = "97201";
    private String json = "{\"coord\":{\"lon\":-122.67,\"lat\":45.52},\"weather\":[{\"id\":701,\"main\":\"Mist\",\"description\":\"mist\",\"icon\":\"50n\"}],\"base\":\"stations\",\"main\":{\"temp\":277.33,\"pressure\":1031,\"humidity\":92,\"temp_min\":275.15,\"temp_max\":278.75},\"visibility\":14484,\"wind\":{\"speed\":0.96,\"deg\":34.5018},\"clouds\":{\"all\":1},\"dt\":1541652960,\"sys\":{\"type\":1,\"id\":2963,\"message\":0.0058,\"country\":\"US\",\"sunrise\":1541689229,\"sunset\":1541724468},\"id\":420029235,\"name\":\"Portland\",\"cod\":200}";

    @Mock
    RestTemplate restTemplate;

    @Mock
    OpenWeatherResponseParser parser;

    @InjectMocks
    WeatherHttpClientAdapter target;

    @Test
    public void shouldCallTemperatureEndpointAndParseResponse() {
        when(restTemplate.getForObject(anyString(), any())).thenReturn(json);
        when(parser.parse(anyString())).thenReturn(new LocationConditions("pdx", "72F", "45", "-122"));

        LocationConditions locationConditions = target.get(zipCode);

        assert(locationConditions != null);
        assert(locationConditions.getCity().equals("pdx"));
        assert(locationConditions.getTemperature().equals("72F"));
        assert(locationConditions.getLatitude().equals("45"));
        assert(locationConditions.getLongitude().equals("-122"));
        verify(restTemplate, times(1)).getForObject(anyString(), any());
        verify(parser, times(1)).parse(anyString());
    }

    @Test
    public void shouldReturnUnavailableForErrors() {
        when(restTemplate.getForObject(anyString(), any())).thenThrow(RestClientException.class);

        LocationConditions locationConditions = target.get(zipCode);

        assert(locationConditions != null);
        assert(locationConditions.getCity().equals("unknown"));
        assert(locationConditions.getTemperature().equals("unavailable"));
        assert(locationConditions.getLatitude().equals("unavailable"));
        assert(locationConditions.getLongitude().equals("unavailable"));
    }
}