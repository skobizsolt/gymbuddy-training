package com.gymbuddy.training.service;

import com.gymbuddy.training.model.util.WorkoutDetailsResponse;

/**
 * Workout utility service.
 */
public interface WorkoutUtilService {

    /**
     * Method for getting estimated time and number of steps of a workout.
     *
     * @param workoutId workout ID
     * @return {@link WorkoutDetailsResponse}.
     */
    WorkoutDetailsResponse getGeneralDetails(Long workoutId);
}
