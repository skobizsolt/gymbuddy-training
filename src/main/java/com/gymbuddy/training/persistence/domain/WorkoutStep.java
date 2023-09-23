package com.gymbuddy.training.persistence.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * Entity class that represents steps of the created workout.
 */
@Entity(name = "workout_step")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkoutStep {
    @EmbeddedId WorkoutStepId workoutStepId;
    @NotNull Integer stepNumber;
    @NotNull String stepName;
    String details;
    @Enumerated(value = EnumType.STRING)
    @NotNull WorkoutType workoutType;
    @NotNull Integer estimatedTime;
}
