package com.gymbuddy.training.controller;

import com.gymbuddy.training.model.steps.ChangeWorkoutStepRequest;
import com.gymbuddy.training.model.steps.WorkoutStepResponse;
import com.gymbuddy.training.service.WorkoutStepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Operation(summary = "List all steps for a workout", security = {@SecurityRequirement(name = "token")})
    public ResponseEntity<List<WorkoutStepResponse>> getAllSteps(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken,
            @PathVariable("workoutId") @NotNull @Valid final Long workoutId) {
        log.info("Endpoint::getAllSteps invoked. workoutId: {}", workoutId);
        final List<WorkoutStepResponse> steps = workoutStepService.getAllSteps(workoutId);
        return ResponseEntity.ok(steps);
    }

    @GetMapping("/{stepId}")
    @Operation(summary = "Gets a step associated with the selected workout", security = {@SecurityRequirement(name = "token")})
    public ResponseEntity<WorkoutStepResponse> getStep(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken,
                                                       @PathVariable("workoutId") @NotNull @Valid final Long workoutId,
                                                       @PathVariable("stepId") @NotNull @Valid final Long stepId) {
        log.info("Endpoint::getStep invoked. workoutId: {}, stepId: {}", workoutId, stepId);
        final WorkoutStepResponse step = workoutStepService.getStep(workoutId, stepId);
        return ResponseEntity.ok(step);
    }

    @PostMapping
    @Operation(summary = "Creates a new step under the workout", security = {@SecurityRequirement(name = "token")})
    public ResponseEntity<WorkoutStepResponse> addStep(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken,
                                                       @PathVariable("workoutId") @NotNull @Valid final Long workoutId,
                                                       @RequestBody @Valid final ChangeWorkoutStepRequest creatableWorkoutStepDto) {
        log.info("Endpoint::newStep invoked. workoutId: {}, creatableWorkoutStepDto: {}", workoutId, creatableWorkoutStepDto);
        final WorkoutStepResponse step = workoutStepService.addStep(workoutId, creatableWorkoutStepDto);
        return ResponseEntity.ok(step);
    }

    @PutMapping("/{stepId}")
    @Operation(summary = "Edits an existing step", security = {@SecurityRequirement(name = "token")})
    public ResponseEntity<WorkoutStepResponse> editStep(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken,
                                                        @PathVariable("workoutId") @NotNull @Valid final Long workoutId,
                                                        @PathVariable("stepId") @NotNull @Valid final Long stepId,
                                                        @RequestBody @Valid final ChangeWorkoutStepRequest editableWorkoutStepDto) {
        log.info("Endpoint::editStep invoked. " +
                "workoutId: {}, stepId: {}, editableWorkoutStepDto: {}", workoutId, stepId, editableWorkoutStepDto);
        final WorkoutStepResponse step = workoutStepService.editStep(workoutId, stepId, editableWorkoutStepDto);
        return ResponseEntity.ok(step);
    }

    @DeleteMapping("/{stepId}")
    @Operation(summary = "Deletes a step from a workout", security = {@SecurityRequirement(name = "token")})
    public ResponseEntity<Void> deleteStep(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken,
                                           @PathVariable("workoutId") @NotNull @Valid final Long workoutId,
                                           @PathVariable("stepId") @NotNull @Valid final Long stepId) {
        log.info("Endpoint::deleteStep invoked. workoutId: {}, stepId: {}", workoutId, stepId);
        workoutStepService.deleteStep(workoutId, stepId);
        return ResponseEntity.ok().build();
    }
}
