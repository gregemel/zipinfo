package com.emelgreg.zipinfo.handler;

import com.emelgreg.zipinfo.ports.ElevationServiceClient;
import com.emelgreg.zipinfo.ports.WeatherServiceClient;
import com.emelgreg.zipinfo.ports.TimeZoneServiceClient;
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
    WeatherServiceClient weatherServiceClientServiceMock;

    @Mock
    TimeZoneServiceClient timeZoneServiceClientServiceMock;

    @Mock
    ElevationServiceClient elevationServiceClientServiceMock;

    @InjectMocks
    ZipWeatherHandlerImpl target;

    @Test
    public void shouldCallTemperatureServiceWithZip() {
        when(weatherServiceClientServiceMock.get(zipCode)).thenReturn(new Location("pdx", "72F", "45", "-122"));

        Weather infoText = target.handleWeatherRequest(zipCode);

        assert(infoText != null);
        assert(!infoText.getCity().isEmpty());
        assert(!infoText.getTemperature().isEmpty());
        verify(weatherServiceClientServiceMock, times(1)).get(anyString());
    }

    @Test
    public void shouldCallTimeZoneServiceWithZip() {
        when(weatherServiceClientServiceMock.get(zipCode)).thenReturn(new Location("pdx", "72F", "45", "-122"));
        when(timeZoneServiceClientServiceMock.get(any(Location.class))).thenReturn("Pacific");

        Weather infoText = target.handleWeatherRequest(zipCode);

        assert(infoText != null);
        assert(!infoText.getTimeZone().isEmpty());
        verify(timeZoneServiceClientServiceMock, times(1)).get(any(Location.class));
    }

    @Test
    public void shouldCallElevationServiceWithZip() {
        when(weatherServiceClientServiceMock.get(zipCode)).thenReturn(new Location("pdx", "72F", "45", "-122"));
        when(elevationServiceClientServiceMock.get(any(Location.class))).thenReturn("200ft");

        Weather infoText = target.handleWeatherRequest(zipCode);

        assert(infoText != null);
        assert(!infoText.getElevation().isEmpty());
        verify(elevationServiceClientServiceMock, times(1)).get(any(Location.class));
    }
}