package com.gymbuddy.training.service;

import com.gymbuddy.training.model.*;

/**
 * Interface class for Workout service.
 */
public interface WorkoutService {
    /**
     * Method for getting all workouts.
     *
     * @return {@link WorkoutListResponse} instance.
     */
    WorkoutListResponse getAllWorkouts();

    /**
     * Method for getting a workout.
     *
     * @param workoutId ID of the workout
     * @return {@link WorkoutResponse} instance.
     */
    WorkoutResponse getWorkout(final Long workoutId);

    /**
     * Method for creating a workout.
     *
     * @param creatableWorkout new workout data
     * @param userId           ID of the user who creates the workout
     * @return {@link WorkoutResponse} instance.
     */
    DetailedWorkoutsResponse createWorkout(final CreateWorkoutRequest creatableWorkout,
                                           final String userId);

    /**
     * Method for editing an existing workout.
     *
     * @param workoutId        ID of the workout
     * @param updatableWorkout edited workout data
     * @return {@link WorkoutResponse} instance.
     */
    WorkoutResponse editWorkout(final EditWorkoutRequest updatableWorkout,
                                final Long workoutId);

    /**
     * Endpoint for deleting a workout.
     *
     * @param workoutId ID of the workout
     */
    void deleteWorkout(Long workoutId);
}
