package com.timeconversion.service;


import com.timeconversion.entity.TimeResponse;

public interface TimeConversionService {
    TimeResponse convertToWords(String time);
}
