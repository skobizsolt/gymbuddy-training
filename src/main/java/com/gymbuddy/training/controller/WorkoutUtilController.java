package com.gymbuddy.training.controller;

import com.gymbuddy.training.model.util.WorkoutDetailsResponse;
import com.gymbuddy.training.service.WorkoutUtilService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workouts")
@Slf4j
@RequiredArgsConstructor
public class WorkoutUtilController {

    private final WorkoutUtilService workoutUtilService;

    @GetMapping("/{workoutId}/details")
    public ResponseEntity<WorkoutDetailsResponse> getGeneralDetails(@PathVariable("workoutId") @NotNull @Valid final Long workoutId) {
        log.info("Endpoint::getGeneralDetails invoked. workoutId: {}", workoutId);
        return ResponseEntity.ok(workoutUtilService.getGeneralDetails(workoutId));
    }
}
