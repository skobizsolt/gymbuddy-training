package com.gymbuddy.training.service;

import com.gymbuddy.training.dto.ChangeWorkoutDto;
import com.gymbuddy.training.dto.DetailedWorkoutsDto;
import com.gymbuddy.training.dto.WorkoutDto;
import com.gymbuddy.training.dto.WorkoutsDto;

/**
 * Interface class for Workout service.
 */
public interface WorkoutService {
    /**
     * Method for getting all workouts.
     *
     * @return {@link WorkoutsDto} instance.
     */
    WorkoutsDto getAllWorkouts();

    /**
     * Method for getting a workout.
     *
     * @param workoutId ID of the workout
     * @return {@link WorkoutDto} instance.
     */
    WorkoutDto getWorkout(final Long workoutId);

    /**
     * Method for creating a workout.
     *
     * @param creatableWorkout new workout data
     * @param userId           ID of the user who creates the workout
     * @return {@link WorkoutDto} instance.
     */
    DetailedWorkoutsDto createWorkout(final ChangeWorkoutDto creatableWorkout,
                                      final Long userId);

    /**
     * Method for editing an existing workout.
     *
     * @param workoutId        ID of the workout
     * @param updatableWorkout edited workout data
     * @return {@link WorkoutDto} instance.
     */
    WorkoutDto editWorkout(final ChangeWorkoutDto updatableWorkout,
                           final Long workoutId);

    /**
     * Endpoint for deleting a workout.
     *
     * @param workoutId ID of the workout
     */
    void deleteWorkout(Long workoutId);
}
