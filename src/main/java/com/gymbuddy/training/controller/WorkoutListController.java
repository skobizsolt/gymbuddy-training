package com.gymbuddy.training.controller;

import com.gymbuddy.training.model.WorkoutListResponse;
import com.gymbuddy.training.service.WorkoutListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<WorkoutListResponse> getAllWorkouts() {
        log.info("Endpoint::getAllWorkouts invoked.");
        final WorkoutListResponse workouts = workoutListService.getAllWorkouts();
        return ResponseEntity.ok(workouts);
    }
}
