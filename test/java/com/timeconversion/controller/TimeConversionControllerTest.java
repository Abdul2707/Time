package com.timeconversion.controller;


import com.timeconversion.entity.TimeResponse;
import com.timeconversion.exception.TimeConversionException;
import com.timeconversion.service.TimeConversionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TimeConversionControllerTest {

    @Mock
    private TimeConversionService timeConversionService;

    @InjectMocks
    private TimeConversionController timeConversionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConvertTime_Success() {
        // Mock the service layer response
        when(timeConversionService.convertToWords("08:34"))
                .thenReturn(new TimeResponse("It's eight thirty four"));

        // Call the controller method
        TimeResponse response = timeConversionController.convertTime("08:34");

        // Verify the service layer was called and the response
        verify(timeConversionService, times(1)).convertToWords("08:34");
        assertEquals("It's eight thirty four", response.getMessage());
    }

    @Test
    public void testConvertTime_InvalidFormat() {
        // Mock the service layer to throw an exception
        when(timeConversionService.convertToWords("8:34"))
                .thenThrow(new TimeConversionException("Invalid time format. Please use HH:mm."));

        // Call the controller method with an invalid time format
        TimeResponse response = timeConversionController.convertTime("8:34");

        // Verify the service layer was called and the response
        verify(timeConversionService, times(1)).convertToWords("8:34");
        assertEquals("Invalid time format. Please use HH:mm.", response.getMessage());
    }
}

