package com.gymbuddy.training.model.util;

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
public class WorkoutDetailsResponse {
    Long estimatedTimeInMinutes;
    Long totalSteps;
}
