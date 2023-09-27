package com.gymbuddy.training.controller;

import com.gymbuddy.training.model.WorkoutListResponse;
import com.gymbuddy.training.persistence.domain.WorkoutCategory;
import com.gymbuddy.training.service.WorkoutListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * Endpoint for listing all workouts.
     *
     * @param category {@link WorkoutCategory}
     * @return {@link WorkoutListResponse} response.
     */
    @GetMapping(name = "getWorkoutsByCategory", params = {"category"})
    public ResponseEntity<WorkoutListResponse> getWorkoutsByCategory(
            @RequestParam(value = "category") final WorkoutCategory category) {
        log.info("Endpoint::getWorkoutsByCategory invoked. Category: {}", category);
        final WorkoutListResponse workouts = workoutListService.getAllWorkoutsByCategory(category);
        return ResponseEntity.ok(workouts);
    }

    /**
     * Endpoint for listing all workouts.
     *
     * @param userId user identifier
     * @return {@link WorkoutListResponse} response.
     */
    @GetMapping(name = "getWorkoutsByUserId", params = {"userId"})
    public ResponseEntity<WorkoutListResponse> getWorkoutsByUserId(
            @RequestParam(value = "userId") final String userId) {
        log.info("Endpoint::getWorkoutsByUserId invoked. UserId: {}", userId);
        final WorkoutListResponse workouts = workoutListService.getAllWorkoutsByUserId(userId);
        return ResponseEntity.ok(workouts);
    }
}
