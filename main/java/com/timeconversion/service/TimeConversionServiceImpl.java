package com.timeconversion.service;


import com.timeconversion.entity.TimeResponse;
import com.timeconversion.exception.TimeConversionException;
import com.timeconversion.util.TimeUtil;
import org.springframework.stereotype.Service;

@Service
public class TimeConversionServiceImpl implements TimeConversionService {

    @Override
    public TimeResponse convertToWords(String time) {
        try {
            String convertedTime = TimeUtil.convertToWords(time);
            return new TimeResponse("It's " + convertedTime);
        } catch (TimeConversionException e) {
            throw new TimeConversionException("Invalid time format. Please use HH:mm.", e);
        }
    }
}