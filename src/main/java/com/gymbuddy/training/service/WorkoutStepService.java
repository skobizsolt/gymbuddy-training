package com.gymbuddy.training.service;

import com.gymbuddy.training.model.steps.ChangeWorkoutStepRequest;
import com.gymbuddy.training.model.steps.WorkoutStepResponse;

import java.util.List;

/**
 * Interface class for Workout steps service.
 */
public interface WorkoutStepService {
    List<WorkoutStepResponse> getAllSteps(Long workoutId);

    WorkoutStepResponse getStep(Long workoutId,
                                Long stepNumber);

    WorkoutStepResponse addStep(Long workoutId,
                                ChangeWorkoutStepRequest creatableWorkoutStepDto);


    WorkoutStepResponse editStep(Long workoutId,
                                 Long stepNumber,
                                 ChangeWorkoutStepRequest editableWorkoutStepDto);

    void deleteStep(Long workoutId,
                    Long stepNumber);
}
