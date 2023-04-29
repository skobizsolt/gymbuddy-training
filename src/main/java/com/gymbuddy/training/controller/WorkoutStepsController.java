package com.gymbuddy.training.controller;

import com.gymbuddy.training.dto.steps.ChangeWorkoutStepDto;
import com.gymbuddy.training.dto.steps.WorkoutStepDto;
import com.gymbuddy.training.dto.steps.WorkoutStepsDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing Workouts steps.
 */
@RestController
@RequestMapping("/workouts/{workoutId}/steps")
@Slf4j
@AllArgsConstructor
public class WorkoutStepsController {

    @GetMapping
    public ResponseEntity<WorkoutStepsDto> getAllSteps(@PathVariable("workoutId") @NotNull @Valid final Long workoutId) {
        return ResponseEntity.ok(WorkoutStepsDto.builder().build());
    }

    @GetMapping("/{stepId}")
    public ResponseEntity<WorkoutStepDto> getStep(@PathVariable("workoutId") @NotNull @Valid final Long workoutId,
                                                  @PathVariable("stepId") @NotNull @Valid final Long stepId) {
        return ResponseEntity.ok(WorkoutStepDto.builder().build());
    }

    @PostMapping("/create")
    public ResponseEntity<WorkoutStepDto> newStep(@PathVariable("workoutId") @NotNull @Valid final Long workoutId,
                                                  @RequestBody @Valid final ChangeWorkoutStepDto changeWorkoutStepDto) {
        return ResponseEntity.ok(WorkoutStepDto.builder().build());
    }

    @PutMapping("/{stepId}/edit")
    public ResponseEntity<WorkoutStepDto> editWorkout(@PathVariable("workoutId") @NotNull @Valid final Long workoutId,
                                                      @PathVariable("stepId") @NotNull @Valid final Long stepId,
                                                      @RequestBody @Valid final ChangeWorkoutStepDto changeWorkoutStepDto) {
        return ResponseEntity.ok(WorkoutStepDto.builder().build());
    }

    @DeleteMapping("/{stepId}/delete")
    public ResponseEntity<Void> deleteWorkout(@PathVariable("workoutId") @NotNull @Valid final Long workoutId,
                                              @PathVariable("stepId") @NotNull @Valid final Long stepId) {
        return ResponseEntity.ok().build();
    }
}
