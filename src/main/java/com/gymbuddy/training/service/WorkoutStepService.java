package com.gymbuddy.training.service;

import com.gymbuddy.training.dto.steps.ChangeWorkoutStepDto;
import com.gymbuddy.training.dto.steps.WorkoutStepDto;

import java.util.List;

/**
 * Interface class for Workout steps service.
 */
public interface WorkoutStepService {
    List<WorkoutStepDto> getAllSteps(Long workoutId);

    WorkoutStepDto getStep(Long workoutId,
                           Long stepNumber);

    WorkoutStepDto addStep(Long workoutId,
                           ChangeWorkoutStepDto creatableWorkoutStepDto);


    WorkoutStepDto editStep(Long workoutId,
                            Long stepNumber,
                            ChangeWorkoutStepDto editableWorkoutStepDto);

    void deleteStep(Long workoutId,
                    Long stepNumber);
}
