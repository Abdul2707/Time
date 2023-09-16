package com.timeconversion.util;


import com.timeconversion.exception.TimeConversionException;

public class TimeUtil {
    private static final String[] NUMBERS = {
            "zero", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine"
    };

    private static final String[] TEENS = {
            "ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static final String[] TENS = {
            "", "", "twenty", "thirty", "forty",
            "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    public static String convertToWords(String time) {
        // Ensure the input time matches the expected format "HH:mm"
        if (!time.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            throw new TimeConversionException("Invalid time format. Please use HH:mm.");
        }

        // Split the time into hours and minutes
        String[] parts = time.split(":");

        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            throw new TimeConversionException("Invalid time. Hours should be between 0-23, and minutes between 0-59.");
        }

        String hourWords = convertHourToWords(hours);
        String minuteWords = convertMinuteToWords(minutes);

        return "It's " + hourWords + " " + minuteWords;
    }


    private static String convertHourToWords(int hour) {
        if (hour == 0) {
            return "midnight";
        } else if (hour == 12) {
            return "midday";
        }

        int tensDigit = hour / 10;
        int onesDigit = hour % 10;

        if (tensDigit == 1) {
            return TEENS[onesDigit];
        } else {
            return TENS[tensDigit] + (onesDigit == 0 ? "" : " " + NUMBERS[onesDigit]);
        }
    }

    private static String convertMinuteToWords(int minute) {
        if (minute == 0) {
            return "";
        } else if (minute < 10) {
            return "oh " + NUMBERS[minute];
        } else if (minute < 20) {
            return TEENS[minute - 10];
        } else {
            int tensDigit = minute / 10;
            int onesDigit = minute % 10;
            return TENS[tensDigit] + (onesDigit == 0 ? "" : " " + NUMBERS[onesDigit]);
        }
    }
}

