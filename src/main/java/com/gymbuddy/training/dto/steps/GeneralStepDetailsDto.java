package com.gymbuddy.training.dto.steps;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeneralStepDetailsDto {
    Integer estimatedTimeInMinutes;
    Integer totalSteps;
}
