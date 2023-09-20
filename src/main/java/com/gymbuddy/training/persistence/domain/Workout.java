package com.gymbuddy.training.persistence.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity class that represents the created workout.
 */
@Entity(name = "workout")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Workout {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id Long workoutId;
    @NotNull Long userId;
    @NotNull String title;
    @NotNull LocalDate registeredOn;
    @NotNull LocalDateTime lastModified;
    @Enumerated(value = EnumType.STRING)
    @NotNull WorkoutDifficulty difficulty;
    @Nullable Integer estimatedTimeInMinutes;
}
