package com.gymbuddy.training.dto;

import com.gymbuddy.training.persistence.domain.Workout;
import com.gymbuddy.training.persistence.domain.WorkoutDifficulty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO class that stores a record from {@link Workout}.
 */
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkoutDto {
    String title;
    LocalDate registeredOn;
    LocalDateTime lastModified;
    WorkoutDifficulty difficulty;
    Integer estimatedTimeInMinutes;
}
