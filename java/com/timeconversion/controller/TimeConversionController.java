package com.timeconversion.controller;


import com.timeconversion.entity.TimeResponse;
import com.timeconversion.service.TimeConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Time Conversion API")
@RestController
public class TimeConversionController {

    private final TimeConversionService timeConversionService;

    @Autowired
    public TimeConversionController(TimeConversionService timeConversionService) {
        this.timeConversionService = timeConversionService;
    }

    @ApiOperation(value = "Convert Time to Words")
    @GetMapping("/convertTime")
    public TimeResponse convertTime(@RequestParam String time) {
        return timeConversionService.convertToWords(time);
    }
}

