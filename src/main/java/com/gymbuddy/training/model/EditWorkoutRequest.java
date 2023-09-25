package com.gymbuddy.training.model;

import com.gymbuddy.training.persistence.domain.Workout;
import com.gymbuddy.training.persistence.domain.WorkoutCategory;
import com.gymbuddy.training.persistence.domain.WorkoutDifficulty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * DTO class that stores a changeable record from {@link Workout}.
 */
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EditWorkoutRequest {
    String title;
    String description;
    WorkoutCategory category;
    WorkoutDifficulty difficulty;
}
