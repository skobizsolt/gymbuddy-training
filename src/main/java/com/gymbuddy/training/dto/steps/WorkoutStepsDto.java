package com.gymbuddy.training.dto.steps;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * DTO class that stores a list of {@link WorkoutStepDto}.
 */
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkoutStepsDto {
    List<WorkoutStepDto> steps;
}
