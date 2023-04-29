package com.gymbuddy.training.controller;

import com.gymbuddy.training.dto.ChangeWorkoutDto;
import com.gymbuddy.training.dto.WorkoutDto;
import com.gymbuddy.training.dto.WorkoutsDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing Workouts.
 */
@RestController
@RequestMapping("/workouts")
@Slf4j
@AllArgsConstructor
public class WorkoutController {

    @GetMapping
    public ResponseEntity<WorkoutsDto> getAllWorkouts() {
        return ResponseEntity.ok(WorkoutsDto.builder().build());
    }

    @GetMapping("/{workoutId}")
    public ResponseEntity<WorkoutDto> getWorkout(@PathVariable("workoutId") @NotNull @Valid final Long workoutId) {
        return ResponseEntity.ok(WorkoutDto.builder().build());
    }

    @PostMapping("/create")
    public ResponseEntity<WorkoutDto> createWorkout(@RequestBody @Valid final ChangeWorkoutDto workout,
                                                    @RequestParam("userId") @NotNull @Valid final Long userId) {
        return ResponseEntity.ok(WorkoutDto.builder().build());
    }

    @PutMapping("/{workoutId}/edit")
    public ResponseEntity<WorkoutDto> editWorkout(@PathVariable("workoutId") @NotNull @Valid final Long workoutId,
                                                  @RequestBody @Valid final ChangeWorkoutDto workout) {
        return ResponseEntity.ok(WorkoutDto.builder().build());
    }

    @DeleteMapping("/{workoutId}/delete")
    public ResponseEntity<Void> deleteWorkout(@PathVariable("workoutId") @NotNull @Valid final Long workoutId) {
        return ResponseEntity.ok().build();
    }
}
