package com.gymbuddy.training.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Arrays;

@Getter
public enum Errors {

    ENTITY_NOT_FOUND(1L, "Entity not found in DB!", HttpStatus.BAD_REQUEST),
    WORKOUT_NOT_FOUND(2L, "Workout not found!", HttpStatus.BAD_REQUEST),
    WORKOUT_STEP_NOT_FOUND(3L, "Workout step not found!", HttpStatus.BAD_REQUEST),
    UNEXPECTED_ERROR(999L, "Unexpected error occurred!", HttpStatus.INTERNAL_SERVER_ERROR);

    private final Long errorCode;
    private final String cause;
    final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp = ZonedDateTime.now();

    Errors(final Long errorCode, final String cause, final HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.cause = cause;
        this.httpStatus = httpStatus;
    }

    public static Errors getByCode(final Long value) {
        for (Errors errors : Errors.values()) {
            if (value.equals(errors.getErrorCode())) {
                return errors;
            }
        }
        return UNEXPECTED_ERROR;
    }

    public static Errors getError(Errors value) {
        if (value == null) {
            return null;
        }
        for (Errors errors : Errors.values()) {
            if (value == errors) {
                return errors;
            }
        }
        return UNEXPECTED_ERROR;
    }
}
