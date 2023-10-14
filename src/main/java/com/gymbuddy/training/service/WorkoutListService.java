package com.gymbuddy.training.service;

import com.gymbuddy.training.model.WorkoutListResponse;

public interface WorkoutListService {

    /**
     * Method for getting all workouts.
     *
     * @return {@link WorkoutListResponse} instance.
     */
    WorkoutListResponse getAllWorkouts();
}
