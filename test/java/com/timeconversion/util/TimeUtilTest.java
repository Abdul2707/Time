package com.timeconversion.util;


import com.timeconversion.exception.TimeConversionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimeUtilTest {

    @Test
    public void testValidTimeConversion() {
        assertEquals("It's eight thirty four", TimeUtil.convertToWords("08:34"));
        assertEquals("It's fifteen forty five", TimeUtil.convertToWords("15:45"));
        assertEquals("It's twenty two five", TimeUtil.convertToWords("22:05"));
        assertEquals("It's midnight", TimeUtil.convertToWords("00:00"));
        assertEquals("It's midday", TimeUtil.convertToWords("12:00"));
    }

    @Test
    public void testInvalidTimeFormat() {
        assertThrows(TimeConversionException.class, () -> TimeUtil.convertToWords("8:34")); // Missing leading zero
        assertThrows(TimeConversionException.class, () -> TimeUtil.convertToWords("0834")); // Missing colon
        assertThrows(TimeConversionException.class, () -> TimeUtil.convertToWords("25:00")); // Hour out of range
        assertThrows(TimeConversionException.class, () -> TimeUtil.convertToWords("15:60")); // Minute out of range
        assertThrows(TimeConversionException.class, () -> TimeUtil.convertToWords("abc:def")); // Non-numeric characters
    }
}

