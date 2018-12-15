package com.emelgreg.zipinfo.core;

import com.emelgreg.zipinfo.models.LocationConditions;
import com.emelgreg.zipinfo.ports.ElevationPort;
import com.emelgreg.zipinfo.ports.CurrentConditionsPort;
import com.emelgreg.zipinfo.ports.TimeZonePort;
import com.emelgreg.zipinfo.models.ZipWeather;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ZipWeatherPortSpec {
    private String zipCode = "98765";

    @Mock
    CurrentConditionsPort weatherServiceClientServiceMock;

    @Mock
    TimeZonePort timeZoneServiceClientServiceMock;

    @Mock
    ElevationPort elevationServiceClientServiceMock;

    @InjectMocks
    ZipWeatherHandler target;

    @Test
    public void shouldCallTemperatureServiceWithZip() {
        when(weatherServiceClientServiceMock.get(zipCode)).thenReturn(new LocationConditions("pdx", "72F", "45", "-122"));

        ZipWeather infoText = target.getWeatherByZip(zipCode);

        assert(infoText != null);
        assert(!infoText.getCity().isEmpty());
        assert(!infoText.getTemperature().isEmpty());
        verify(weatherServiceClientServiceMock, times(1)).get(anyString());
    }

    @Test
    public void shouldCallTimeZoneServiceWithZip() {
        when(weatherServiceClientServiceMock.get(zipCode)).thenReturn(new LocationConditions("pdx", "72F", "45", "-122"));
        when(timeZoneServiceClientServiceMock.get(any(LocationConditions.class))).thenReturn("Pacific");

        ZipWeather infoText = target.getWeatherByZip(zipCode);

        assert(infoText != null);
        assert(!infoText.getTimeZone().isEmpty());
        verify(timeZoneServiceClientServiceMock, times(1)).get(any(LocationConditions.class));
    }

    @Test
    public void shouldCallElevationServiceWithZip() {
        when(weatherServiceClientServiceMock.get(zipCode)).thenReturn(new LocationConditions("pdx", "72F", "45", "-122"));
        when(elevationServiceClientServiceMock.get(any(LocationConditions.class))).thenReturn("200ft");

        ZipWeather infoText = target.getWeatherByZip(zipCode);

        assert(infoText != null);
        assert(!infoText.getElevation().isEmpty());
        verify(elevationServiceClientServiceMock, times(1)).get(any(LocationConditions.class));
    }
}