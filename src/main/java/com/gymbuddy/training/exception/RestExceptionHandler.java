package com.gymbuddy.training.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(ServiceExpection.class)
    public ResponseEntity<Object> handleServiceException(final HttpServletRequest request,
                                                         final ServiceExpection expection) {
        final Map<String, Object> response = new ConcurrentHashMap<>();
        response.put("message", expection.getMessage());
        response.put("timestamp:", Errors.getByCode(expection.getErrorCode()).getTimeStamp());
        log.error("Exception occured at: {}", request.getRequestURL());
        return new ResponseEntity<>(response, expection.getStatusCode());
    }
}
