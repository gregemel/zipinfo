package com.emelgreg.zipinfo.controllers;

import com.emelgreg.zipinfo.models.Weather;
import com.emelgreg.zipinfo.handlers.ZipWeatherHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ZipInfoController.class)
public class ZipInfoControllerSpec {

    private String zipCode = "98765";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZipWeatherHandler zipWeatherHandlerServiceMock;

    @Test
    public void shouldGetZipInformationFromLocationService() throws Exception {
        when(zipWeatherHandlerServiceMock.handleWeatherRequest(zipCode)).thenReturn(
                new Weather("city", "temp", "timezone", "elevation"));

        this.mockMvc.perform(get("/api/v1/zipinfo/" + zipCode)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "At the location city, the temperature is temp, the timezone is timezone, and the elevation is elevation.")));

        verify(zipWeatherHandlerServiceMock, times(1)).handleWeatherRequest(anyString());
    }

    @Test
    public void shouldReturnErrorMessageWhenZipCodeIsLessThan5Digits() throws Exception {
        when(zipWeatherHandlerServiceMock.handleWeatherRequest(zipCode)).thenReturn(
                new Weather("city", "temp", "timezone", "elevation"));

        this.mockMvc.perform(get("/api/v1/zipinfo/1234")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "This service requires a valid zip code.")));

        verify(zipWeatherHandlerServiceMock, times(0)).handleWeatherRequest(anyString());
    }

    @Test
    public void shouldReturn404WhenZipCodeIsNotProvided() throws Exception {
        when(zipWeatherHandlerServiceMock.handleWeatherRequest(zipCode)).thenReturn(
                new Weather("city", "temp", "timezone", "elevation"));

        this.mockMvc.perform(get("/api/v1/zipinfo/")).andDo(print()).andExpect(status().isNotFound());
    }
}