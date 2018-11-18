package com.emelgreg.zipinfo.services;

import com.emelgreg.zipinfo.adapters.TimeZoneServiceClientImpl;
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
public class TimeZoneServiceClientSpec {

    private String latitude = "45.52";
    private String longitude = "-122.67";

    String json = "{\n" +
            "   \"dstOffset\" : 3600,\n" +
            "   \"rawOffset\" : -28800,\n" +
            "   \"status\" : \"OK\",\n" +
            "   \"timeZoneId\" : \"America/Los_Angeles\",\n" +
            "   \"timeZoneName\" : \"Pacific Daylight Time\"\n" +
            "}";

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    TimeZoneServiceClientImpl target;

    @Test
    public void shouldCallTimeZoneEndpointAndParseResponse() {
        when(restTemplate.getForObject(anyString(), any())).thenReturn(json);

        String timeZone = target.get(latitude, longitude);

        assert(timeZone != null);
        assert(timeZone.equals("Pacific Daylight Time"));
        verify(restTemplate, times(1)).getForObject(anyString(), any());
    }

    @Test
    public void shouldReturnUnavailableForErrors() {
        when(restTemplate.getForObject(anyString(), any())).thenThrow(RestClientException.class);

        String timeZone = target.get(latitude, longitude);

        assert(timeZone != null);
        assert(timeZone.equals("unavailable"));
    }
}