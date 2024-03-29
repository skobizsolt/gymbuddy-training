package com.gymbuddy.training.service;

import com.gymbuddy.training.model.ChangeWorkoutRequest;
import com.gymbuddy.training.model.WorkoutResponse;

/**
 * Interface class for Workout service.
 */
public interface WorkoutService {

    /**
     * Method for getting a workout.
     *
     * @param workoutId ID of the workout
     * @return {@link WorkoutResponse} instance.
     */
    WorkoutResponse getWorkout(Long workoutId);

    /**
     * Method for creating a workout.
     *
     * @param creatableWorkout new workout data
     * @param userId           ID of the user who creates the workout
     * @return {@link WorkoutResponse} instance.
     */
    WorkoutResponse createWorkout(ChangeWorkoutRequest creatableWorkout,
                                  String userId);

    /**
     * Method for editing an existing workout.
     *
     * @param workoutId        ID of the workout
     * @param updatableWorkout edited workout data
     * @return {@link WorkoutResponse} instance.
     */
    WorkoutResponse editWorkout(ChangeWorkoutRequest updatableWorkout,
                                Long workoutId);

    /**
     * Endpoint for deleting a workout.
     *
     * @param workoutId ID of the workout
     */
    void deleteWorkout(Long workoutId);
}
