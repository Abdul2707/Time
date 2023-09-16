package com.timeconversion.service;


import com.timeconversion.entity.TimeResponse;
import com.timeconversion.exception.TimeConversionException;
import com.timeconversion.util.TimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TimeConversionServiceTest {

    @Mock
    private TimeUtil timeUtil;

    @InjectMocks
    private TimeConversionServiceImpl timeConversionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConvertToWords_Success() {
        // Mock the TimeUtil's behavior to return a valid conversion
        when(timeUtil.convertToWords("08:34")).thenReturn("eight thirty four");

        // Call the service method
        TimeResponse response = timeConversionService.convertToWords("08:34");

        // Verify the response
        assertEquals("It's eight thirty four", response.getMessage());
    }

    @Test
    public void testConvertToWords_InvalidInput() {
        // Mock the TimeUtil's behavior to throw a TimeConversionException
        when(timeUtil.convertToWords("8:34")).thenThrow(new TimeConversionException("Invalid time format"));

        // Call the service method with an invalid input
        assertThrows(TimeConversionException.class, () -> timeConversionService.convertToWords("8:34"));
    }
}

