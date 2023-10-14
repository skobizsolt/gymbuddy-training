package com.gymbuddy.training.controller;

import com.gymbuddy.training.model.ChangeWorkoutRequest;
import com.gymbuddy.training.model.WorkoutResponse;
import com.gymbuddy.training.service.WorkoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
    @Operation(summary = "Gets a workout by it's id", security = {@SecurityRequirement(name = "token")})
    public ResponseEntity<WorkoutResponse> getWorkout(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken,
                                                      @PathVariable("workoutId") @NotNull @Valid final Long workoutId
    ) {
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
    @Operation(summary = "Creates a new workout", security = {@SecurityRequirement(name = "token")})
    public ResponseEntity<WorkoutResponse> createWorkout(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken,
            @RequestBody @Valid final ChangeWorkoutRequest creatableWorkout,
            @RequestParam("userId") @NotNull @Valid final String userId) {
        log.info("Endpoint::createWorkout invoked. creatableWorkout: {}, userId: {}", creatableWorkout, userId);
        final WorkoutResponse workout = workoutService.createWorkout(creatableWorkout, userId);
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
    @Operation(summary = "Edits an existing workout", security = {@SecurityRequirement(name = "token")})
    public ResponseEntity<WorkoutResponse> editWorkout(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken,
                                                       @PathVariable("workoutId") @NotNull @Valid final Long workoutId,
                                                       @RequestBody @Valid final ChangeWorkoutRequest updatableWorkout) {
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
    @Operation(summary = "Deletes the selected workout", security = {@SecurityRequirement(name = "token")})
    public ResponseEntity<Void> deleteWorkout(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken,
                                              @PathVariable("workoutId") @NotNull @Valid final Long workoutId) {
        log.info("Endpoint::deleteWorkout invoked. workoutId: {}", workoutId);
        workoutService.deleteWorkout(workoutId);
        return ResponseEntity.ok().build();
    }
}
