package com.gymbuddy.training.service;

import com.gymbuddy.training.dto.steps.ChangeWorkoutStepDto;
import com.gymbuddy.training.dto.steps.WorkoutStepDto;
import com.gymbuddy.training.dto.steps.WorkoutStepsDto;

/**
 * Interface class for Workout steps service.
 */
public interface WorkoutStepService {
    WorkoutStepsDto getAllSteps(final Long workoutId);

    WorkoutStepDto getStep(final Long workoutId,
                           final Long stepNumber);

    WorkoutStepDto addStep(final Long workoutId,
                           final ChangeWorkoutStepDto creatableWorkoutStepDto);

    WorkoutStepDto editStep(final Long workoutId,
                            final Long stepNumber,
                            final ChangeWorkoutStepDto editableWorkoutStepDto);

    void deleteStep(final Long workoutId,
                    final Long stepNumber);
}
