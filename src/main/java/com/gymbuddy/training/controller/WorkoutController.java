package com.gymbuddy.training.controller;

import com.gymbuddy.training.model.*;
import com.gymbuddy.training.persistence.domain.WorkoutCategory;
import com.gymbuddy.training.service.WorkoutService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing Workouts.
 */
@RestController
@RequestMapping("/workouts")
@Slf4j
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    /**
     * Endpoint for GET a workout by ID.
     *
     * @param workoutId ID of the workout
     * @return {@link WorkoutResponse} response.
     */
    @GetMapping("/{workoutId}")
    public ResponseEntity<WorkoutResponse> getWorkout(@PathVariable("workoutId") @NotNull @Valid final Long workoutId) {
        log.info("Endpoint::getWorkout invoked. workoutId: {}", workoutId);
        final WorkoutResponse workout = workoutService.getWorkout(workoutId);
        return ResponseEntity.ok(workout);
    }

    /**
     * Endpoint for creating a workout.
     *
     * @param creatableWorkout new workout data
     * @param userId           ID of the user who creates the workout
     * @return {@link WorkoutResponse} response.
     */
    @PostMapping
    public ResponseEntity<DetailedWorkoutsResponse> createWorkout(
            @RequestBody @Valid final CreateWorkoutRequest creatableWorkout,
            @RequestParam("userId") @NotNull @Valid final String userId) {
        log.info("Endpoint::createWorkout invoked. creatableWorkout: {}, userId: {}", creatableWorkout, userId);
        final DetailedWorkoutsResponse workout = workoutService.createWorkout(creatableWorkout, userId);
        return ResponseEntity.ok(workout);
    }

    /**
     * Endpoint for editing an existing workout.
     *
     * @param workoutId        ID of the workout
     * @param updatableWorkout edited workout data
     * @return {@link WorkoutResponse} response.
     */
    @PutMapping("/{workoutId}")
    public ResponseEntity<WorkoutResponse> editWorkout(@PathVariable("workoutId") @NotNull @Valid final Long workoutId,
                                                       @RequestBody @Valid final EditWorkoutRequest updatableWorkout) {
        log.info("Endpoint::editWorkout invoked. updatableWorkout: {}, workoutId: {}", updatableWorkout, workoutId);
        final WorkoutResponse workout = workoutService.editWorkout(updatableWorkout, workoutId);
        return ResponseEntity.ok(workout);
    }

    /**
     * Endpoint for deleting a workout.
     *
     * @param workoutId ID of the workout
     * @return 200 OK.
     */
    @DeleteMapping("/{workoutId}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable("workoutId") @NotNull @Valid final Long workoutId) {
        log.info("Endpoint::deleteWorkout invoked. workoutId: {}", workoutId);
        workoutService.deleteWorkout(workoutId);
        return ResponseEntity.ok().build();
    }
}
