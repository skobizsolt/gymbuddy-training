package com.gymbuddy.training.model;

import com.gymbuddy.training.model.steps.WorkoutStepResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DetailedWorkoutsResponse {
    WorkoutResponse workout;
    List<WorkoutStepResponse> steps;
}
