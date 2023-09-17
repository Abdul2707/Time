package com.timeconversion.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class TimeConversionException extends RuntimeException {
    public   TimeConversionException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeConversionException(String message) {
    }

}
