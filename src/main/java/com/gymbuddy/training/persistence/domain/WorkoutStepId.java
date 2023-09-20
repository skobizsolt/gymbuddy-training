package com.gymbuddy.training.persistence.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkoutStepId {
    @NotNull Long stepId;
    @NotNull Long workoutId;
}
