package com.gymbuddy.training.service;

import com.gymbuddy.training.model.WorkoutListResponse;
import com.gymbuddy.training.persistence.domain.WorkoutCategory;

public interface WorkoutListService {

    /**
     * Method for getting all workouts.
     *
     * @return {@link WorkoutListResponse} instance.
     */
    WorkoutListResponse getAllWorkouts();

    /**
     * Method for getting all workouts by category.
     *
     * @param category {@link WorkoutCategory}
     * @return {@link WorkoutListResponse} instance.
     */
    WorkoutListResponse getAllWorkoutsByCategory(WorkoutCategory category);

    /**
     * Method for getting all workouts by user.
     *
     * @param userId user identifier
     * @return {@link WorkoutListResponse} instance.
     */
    WorkoutListResponse getAllWorkoutsByUserId(String userId);
}
