package com.gymbuddy.training.model.steps;

import com.gymbuddy.training.persistence.domain.WorkoutStep;
import com.gymbuddy.training.persistence.domain.WorkoutType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * DTO class that stores a changeable record from {@link WorkoutStep}.
 */
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangeWorkoutStepRequest {
    String stepName;
    String details;
    WorkoutType workoutType;
    Integer estimatedTime;
}
