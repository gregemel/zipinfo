package com.emelgreg.zipinfo.services;

import com.emelgreg.zipinfo.adapters.ElevationServiceClient;
import com.emelgreg.zipinfo.adapters.TemperatureServiceClient;
import com.emelgreg.zipinfo.adapters.TimeZoneServiceClient;
import com.emelgreg.zipinfo.handlers.ZipWeatherHandlerImpl;
import com.emelgreg.zipinfo.models.Location;
import com.emelgreg.zipinfo.models.Weather;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ZipWeatherHandlerSpec {
    private String zipCode = "98765";

    @Mock
    TemperatureServiceClient temperatureServiceClientServiceMock;

    @Mock
    TimeZoneServiceClient timeZoneServiceClientServiceMock;

    @Mock
    ElevationServiceClient elevationServiceClientServiceMock;

    @InjectMocks
    ZipWeatherHandlerImpl target;

    @Test
    public void shouldCallTemperatureServiceWithZip() {
        when(temperatureServiceClientServiceMock.get(zipCode)).thenReturn(new Location("pdx", "72F", "45", "-122"));

        Weather infoText = target.handleWeatherRequest(zipCode);

        assert(infoText != null);
        assert(!infoText.getCity().isEmpty());
        assert(!infoText.getTemperature().isEmpty());
        verify(temperatureServiceClientServiceMock, times(1)).get(anyString());
    }

    @Test
    public void shouldCallTimeZoneServiceWithZip() {
        when(temperatureServiceClientServiceMock.get(zipCode)).thenReturn(new Location("pdx", "72F", "45", "-122"));
        when(timeZoneServiceClientServiceMock.get("45", "-122")).thenReturn("Pacific");

        Weather infoText = target.handleWeatherRequest(zipCode);

        assert(infoText != null);
        assert(!infoText.getTimeZone().isEmpty());
        verify(timeZoneServiceClientServiceMock, times(1)).get(anyString(), anyString());
    }

    @Test
    public void shouldCallElevationServiceWithZip() {
        when(temperatureServiceClientServiceMock.get(zipCode)).thenReturn(new Location("pdx", "72F", "45", "-122"));
        when(elevationServiceClientServiceMock.get("45", "-122")).thenReturn("200ft");

        Weather infoText = target.handleWeatherRequest(zipCode);

        assert(infoText != null);
        assert(!infoText.getElevation().isEmpty());
        verify(elevationServiceClientServiceMock, times(1)).get(anyString(), anyString());
    }
}