package com.gymbuddy.training.controller;

import com.gymbuddy.training.model.util.WorkoutDetailsResponse;
import com.gymbuddy.training.service.WorkoutUtilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workouts")
@Slf4j
@RequiredArgsConstructor
public class WorkoutUtilController {

    private final WorkoutUtilService workoutUtilService;

    @GetMapping("/{workoutId}/details")
    @Operation(summary = "Get total steps and estimated time for a workout", security = {@SecurityRequirement(name = "token")})
    public ResponseEntity<WorkoutDetailsResponse> getGeneralDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken,
                                                                    @PathVariable("workoutId") @NotNull @Valid final Long workoutId) {
        log.info("Endpoint::getGeneralDetails invoked. workoutId: {}", workoutId);
        return ResponseEntity.ok(workoutUtilService.getGeneralDetails(workoutId));
    }
}
