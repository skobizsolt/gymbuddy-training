package com.gymbuddy.training.mapper;

import com.gymbuddy.training.dto.steps.ChangeWorkoutStepDto;
import com.gymbuddy.training.dto.steps.WorkoutStepDto;
import com.gymbuddy.training.persistence.domain.WorkoutStep;
import com.gymbuddy.training.persistence.domain.WorkoutStepId;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper class for WorkoutStep DTO and Entity methods.
 */
@Mapper(uses = WorkoutStepId.class)
public interface WorkoutStepsDataMapper {

    @Named("workoutStepDto")
    WorkoutStepDto toWorkoutStepDto(final WorkoutStep workoutStep);

    @IterableMapping(qualifiedByName = "workoutStepDto")
    List<WorkoutStepDto> toWorkoutsDto(final List<WorkoutStep> workoutSteps);

    @Mapping(target = "workoutStepId.workoutId", source = "workoutId")
    @Mapping(target = "workoutStepId.stepId", source = "stepNumber")
    WorkoutStep toWorkoutStep(final ChangeWorkoutStepDto creatableWorkoutStepDto,
                              final Long workoutId,
                              final Integer stepNumber);

    void modifyEntity(@MappingTarget final WorkoutStep workoutStepEntity,
                      final ChangeWorkoutStepDto editableWorkoutStepDto);
}
