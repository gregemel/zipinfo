package com.emelgreg.zipinfo.adapter;

import com.emelgreg.zipinfo.adapters.ElevationServiceClientImpl;
import com.emelgreg.zipinfo.models.Location;
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
public class ElevationServiceClientSpec {

    private String latitude = "45.52";
    private String longitude = "-122.67";
    private Location location = new Location("", "", latitude, longitude);

    String json = "{\n" +
            "   \"results\" : [\n" +
            "      {\n" +
            "         \"elevation\" : 1608.637939453125,\n" +
            "         \"location\" : {\n" +
            "            \"lat\" : 39.7391536,\n" +
            "            \"lng\" : -104.9847034\n" +
            "         },\n" +
            "         \"resolution\" : 4.771975994110107\n" +
            "      }\n" +
            "   ],\n" +
            "   \"status\" : \"OK\"\n" +
            "}";

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    ElevationServiceClientImpl target;

    @Test
    public void shouldCallElevationEndpointAndParseResponse() {
        when(restTemplate.getForObject(anyString(), any())).thenReturn(json);

        String timeZone = target.get(location);

        assert(timeZone != null);
        assert(timeZone.equals("5278.0ft"));
        verify(restTemplate, times(1)).getForObject(anyString(), any());
    }

    @Test
    public void shouldReturnUnavailableForErrors() {
        when(restTemplate.getForObject(anyString(), any())).thenThrow(RestClientException.class);

        String timeZone = target.get(location);

        assert(timeZone != null);
        assert(timeZone.equals("unavailable"));
    }

}