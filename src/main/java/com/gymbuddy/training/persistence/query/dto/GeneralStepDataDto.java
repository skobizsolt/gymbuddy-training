package com.gymbuddy.training.persistence.query.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * API response class for general details.
 */
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeneralStepDataDto {
    String estimatedTimeInMinutes;
    String totalSteps;
}