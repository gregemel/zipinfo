package com.emelgreg.zipinfo.services;

import com.emelgreg.zipinfo.models.LocationInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class LocationSpec {
    private String zipCode = "98765";

    @Mock
    Temperature temperatureServiceMock;

    @Mock
    TimeZone timeZoneServiceMock;

    @Mock
    Elevation elevationServiceMock;

    @InjectMocks
    LocationImpl target;

    @Test
    public void shouldCallTemperatureServiceWithZip() {
        when(temperatureServiceMock.get(zipCode)).thenReturn("72F");

        LocationInfo infoText = target.getLocationInfo(zipCode);

        assert(infoText != null);
        assert(!infoText.getTemperature().isEmpty());
        verify(temperatureServiceMock, times(1)).get(anyString());
    }

    @Test
    public void shouldCallTimeZoneServiceWithZip() {
        when(timeZoneServiceMock.get(zipCode)).thenReturn("Pacific");

        LocationInfo infoText = target.getLocationInfo(zipCode);

        assert(infoText != null);
        assert(!infoText.getTimeZone().isEmpty());
        verify(timeZoneServiceMock, times(1)).get(anyString());
    }

    @Test
    public void shouldCallElevationServiceWithZip() {
        when(elevationServiceMock.get(zipCode)).thenReturn("200ft");

        LocationInfo infoText = target.getLocationInfo(zipCode);

        assert(infoText != null);
        assert(!infoText.getElevation().isEmpty());
        verify(elevationServiceMock, times(1)).get(anyString());
    }
}