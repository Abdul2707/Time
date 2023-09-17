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
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        when(timeConversionService.convertToWords("08:34"))
                .thenReturn(new TimeResponse("It's eight thirty four"));

        TimeResponse response = timeConversionController.convertTime("08:34");

        verify(timeConversionService, times(1)).convertToWords("08:34");
        assertEquals("It's eight thirty four", response.getMessage());
    }

    @Test
    public void testConvertTime_InvalidFormat() {
        // Configure the service to throw the exception
        when(timeConversionService.convertToWords("8:34"))
                .thenThrow(new TimeConversionException("Invalid time format. Please use HH:mm."));

        // Call the controller method using assertThrows to capture the exception
        TimeConversionException exception = assertThrows(
                TimeConversionException.class,
                () -> timeConversionController.convertTime("8:34")
        );

        // Verify that the service method was called
        verify(timeConversionService, times(1)).convertToWords("8:34");

        // Verify the exception message
        assertEquals(null, exception.getMessage());
    }
}



