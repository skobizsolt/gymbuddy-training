package com.gymbuddy.training.controller;

import com.gymbuddy.training.model.WorkoutListResponse;
import com.gymbuddy.training.service.WorkoutListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing Workout lists.
 */
@RestController
@RequestMapping("/workouts")
@Slf4j
@RequiredArgsConstructor
public class WorkoutListController {

    private final WorkoutListService workoutListService;

    /**
     * Endpoint for listing all workouts.
     *
     * @return {@link WorkoutListResponse} response.
     */
    @GetMapping
    @Operation(summary = "List all existing workouts", security = {@SecurityRequirement(name = "token")})
    public ResponseEntity<WorkoutListResponse> getAllWorkouts(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken
    ) {
        log.info("Endpoint::getAllWorkouts invoked.");
        final WorkoutListResponse workouts = workoutListService.getAllWorkouts();
        return ResponseEntity.ok(workouts);
    }
}
