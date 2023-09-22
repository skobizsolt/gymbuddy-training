package com.gymbuddy.training.dto;

import com.gymbuddy.training.dto.steps.WorkoutStepDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DetailedWorkoutsDto {
    WorkoutDto workout;
    List<WorkoutStepDto> steps;
}
