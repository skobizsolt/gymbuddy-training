package com.gymbuddy.training.model.util;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

/**
 * API response class for general details.
 */
@Value
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkoutDetailsResponse {
    Long estimatedTimeInMinutes;
    Long totalSteps;
}
