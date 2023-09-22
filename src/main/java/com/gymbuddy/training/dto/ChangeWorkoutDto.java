package com.gymbuddy.training.dto;

import com.gymbuddy.training.dto.steps.ChangeWorkoutStepDto;
import com.gymbuddy.training.persistence.domain.Workout;
import com.gymbuddy.training.persistence.domain.WorkoutCategory;
import com.gymbuddy.training.persistence.domain.WorkoutDifficulty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * DTO class that stores a changeable record from {@link Workout}.
 */
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangeWorkoutDto {
    String title;
    String description;
    WorkoutCategory category;
    WorkoutDifficulty difficulty;
    List<ChangeWorkoutStepDto> steps;
}
