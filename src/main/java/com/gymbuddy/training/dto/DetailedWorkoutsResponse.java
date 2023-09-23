package com.gymbuddy.training.dto;

import com.gymbuddy.training.dto.steps.WorkoutStepResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DetailedWorkoutsResponse {
    WorkoutResponse workout;
    List<WorkoutStepResponse> steps;
}
