package com.gymbuddy.training.controller;

import com.gymbuddy.training.dto.ChangeWorkoutDto;
import com.gymbuddy.training.dto.DetailedWorkoutsDto;
import com.gymbuddy.training.dto.WorkoutDto;
import com.gymbuddy.training.dto.WorkoutsDto;
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
     * Endpoint for listing all workouts.
     *
     * @return {@link WorkoutsDto} response.
     */
    @GetMapping
    public ResponseEntity<WorkoutsDto> getAllWorkouts() {
        log.info("Endpoint::getAllWorkouts invoked.");
        final WorkoutsDto workouts = workoutService.getAllWorkouts();
        return ResponseEntity.ok(workouts);
    }

    /**
     * Endpoint for GET a workout by ID.
     *
     * @param workoutId ID of the workout
     * @return {@link WorkoutDto} response.
     */
    @GetMapping("/{workoutId}")
    public ResponseEntity<WorkoutDto> getWorkout(@PathVariable("workoutId") @NotNull @Valid final Long workoutId) {
        log.info("Endpoint::getWorkout invoked. workoutId: {}", workoutId);
        final WorkoutDto workout = workoutService.getWorkout(workoutId);
        return ResponseEntity.ok(workout);
    }

    /**
     * Endpoint for creating a workout.
     *
     * @param creatableWorkout new workout data
     * @param userId           ID of the user who creates the workout
     * @return {@link WorkoutDto} response.
     */
    @PostMapping("/create")
    public ResponseEntity<DetailedWorkoutsDto> createWorkout(@RequestBody @Valid final ChangeWorkoutDto creatableWorkout,
                                                             @RequestParam("userId") @NotNull @Valid final Long userId) {
        log.info("Endpoint::createWorkout invoked. creatableWorkout: {}, userId: {}", creatableWorkout, userId);
        final DetailedWorkoutsDto workout = workoutService.createWorkout(creatableWorkout, userId);
        return ResponseEntity.ok(workout);
    }

    /**
     * Endpoint for editing an existing workout.
     *
     * @param workoutId        ID of the workout
     * @param updatableWorkout edited workout data
     * @return {@link WorkoutDto} response.
     */
    @PutMapping("/{workoutId}/edit")
    public ResponseEntity<WorkoutDto> editWorkout(@PathVariable("workoutId") @NotNull @Valid final Long workoutId,
                                                  @RequestBody @Valid final ChangeWorkoutDto updatableWorkout) {
        log.info("Endpoint::editWorkout invoked. updatableWorkout: {}, workoutId: {}", updatableWorkout, workoutId);
        final WorkoutDto workout = workoutService.editWorkout(updatableWorkout, workoutId);
        return ResponseEntity.ok(workout);
    }

    /**
     * Endpoint for deleting a workout.
     *
     * @param workoutId ID of the workout
     * @return 200 OK.
     */
    @DeleteMapping("/{workoutId}/delete")
    public ResponseEntity<Void> deleteWorkout(@PathVariable("workoutId") @NotNull @Valid final Long workoutId) {
        log.info("Endpoint::deleteWorkout invoked. workoutId: {}", workoutId);
        workoutService.deleteWorkout(workoutId);
        return ResponseEntity.ok().build();
    }
}
