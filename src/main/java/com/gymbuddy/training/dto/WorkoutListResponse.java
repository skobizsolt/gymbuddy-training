package com.gymbuddy.training.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * DTO class that stores a list of {@link WorkoutResponse}.
 */
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkoutListResponse {
    List<WorkoutResponse> workouts;
}
