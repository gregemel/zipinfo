package com.emelgreg.zipinfo.controllers;

import com.emelgreg.zipinfo.models.LocationInfo;
import com.emelgreg.zipinfo.services.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;


@RunWith(SpringRunner.class)
@WebMvcTest(ZipInfo.class)
public class ZipInfoSpec {

    private String zipCode = "98765";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Location locationServiceMock;

    @Test
    public void shouldGetZipInformationFromLocationService() throws Exception {
        when(locationServiceMock.getLocationInfo(zipCode)).thenReturn(
                new LocationInfo("temp", "timezone", "elevation"));

        this.mockMvc.perform(get("/api/v1/zipinfo/" + zipCode)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "LocationInfo{zip='null', temperature='temp', timeZone='timezone', elevation='elevation'}")));

        verify(locationServiceMock, times(1)).getLocationInfo(anyString());
    }

    @Test
    public void shouldReturnErrorMessageWhenZipCodeIsLessThan5Digits() throws Exception {
        when(locationServiceMock.getLocationInfo(zipCode)).thenReturn(
                new LocationInfo("temp", "timezone", "elevation"));

        this.mockMvc.perform(get("/api/v1/zipinfo/1234")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "This service requires a valid zip code.")));

        verify(locationServiceMock, times(0)).getLocationInfo(anyString());
    }

    @Test
    public void shouldReturn404WhenZipCodeIsNotProvided() throws Exception {
        when(locationServiceMock.getLocationInfo(zipCode)).thenReturn(
                new LocationInfo("temp", "timezone", "elevation"));

        this.mockMvc.perform(get("/api/v1/zipinfo/")).andDo(print()).andExpect(status().isNotFound());
    }
}