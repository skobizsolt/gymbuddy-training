package com.gymbuddy.training.persistence.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * Entity class that represents the created workout.
 */
@Entity(name = "workout")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Workout {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long workoutId;
    @NotNull Long userId;
    @NotNull String title;
    String description;
    @Enumerated(value = EnumType.STRING)
    @NotNull WorkoutCategory category;
    @Enumerated(value = EnumType.STRING)
    @NotNull WorkoutDifficulty difficulty;
    @NotNull Integer estimatedTimeInMinutes;
}
