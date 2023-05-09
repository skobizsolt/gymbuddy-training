package com.gymbuddy.training.controller;

import com.gymbuddy.training.dto.steps.ChangeWorkoutStepDto;
import com.gymbuddy.training.dto.steps.WorkoutStepDto;
import com.gymbuddy.training.dto.steps.WorkoutStepsDto;
import com.gymbuddy.training.service.WorkoutStepService;
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

    private final WorkoutStepService workoutStepService;

    @GetMapping
    public ResponseEntity<WorkoutStepsDto> getAllSteps(@PathVariable("workoutId") @NotNull @Valid final Long workoutId) {
        log.info("Endpoint::getAllSteps invoked. workoutId: {}", workoutId);
        final WorkoutStepsDto steps = workoutStepService.getAllSteps(workoutId);
        return ResponseEntity.ok(steps);
    }

    @GetMapping("/{stepNumber}")
    public ResponseEntity<WorkoutStepDto> getStep(@PathVariable("workoutId") @NotNull @Valid final Long workoutId,
                                                  @PathVariable("stepNumber") @NotNull @Valid final Long stepNumber) {
        log.info("Endpoint::getStep invoked. workoutId: {}, stepNumber: {}", workoutId, stepNumber);
        final WorkoutStepDto step = workoutStepService.getStep(workoutId, stepNumber);
        return ResponseEntity.ok(step);
    }

    @PostMapping("/create")
    public ResponseEntity<WorkoutStepDto> addStep(@PathVariable("workoutId") @NotNull @Valid final Long workoutId,
                                                  @RequestBody @Valid final ChangeWorkoutStepDto creatableWorkoutStepDto) {
        log.info("Endpoint::newStep invoked. workoutId: {}, creatableWorkoutStepDto: {}", workoutId, creatableWorkoutStepDto);
        final WorkoutStepDto step = workoutStepService.addStep(workoutId, creatableWorkoutStepDto);
        return ResponseEntity.ok(step);
    }

    @PutMapping("/{stepNumber}/edit")
    public ResponseEntity<WorkoutStepDto> editStep(@PathVariable("workoutId") @NotNull @Valid final Long workoutId,
                                                   @PathVariable("stepNumber") @NotNull @Valid final Long stepNumber,
                                                   @RequestBody @Valid final ChangeWorkoutStepDto editableWorkoutStepDto) {
        log.info("Endpoint::editStep invoked. " +
                "workoutId: {}, stepNumber: {}, editableWorkoutStepDto: {}", workoutId, stepNumber, editableWorkoutStepDto);
        final WorkoutStepDto step = workoutStepService.editStep(workoutId, stepNumber, editableWorkoutStepDto);
        return ResponseEntity.ok(step);
    }

    @DeleteMapping("/{stepNumber}/delete")
    public ResponseEntity<Void> deleteStep(@PathVariable("workoutId") @NotNull @Valid final Long workoutId,
                                           @PathVariable("stepNumber") @NotNull @Valid final Long stepNumber) {
        log.info("Endpoint::deleteStep invoked. workoutId: {}, stepId: {}", workoutId, stepNumber);
        workoutStepService.deleteStep(workoutId, stepNumber);
        return ResponseEntity.ok().build();
    }
}
