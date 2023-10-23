package com.gymbuddy.training.mapper;

import com.gymbuddy.training.model.steps.ChangeWorkoutStepRequest;
import com.gymbuddy.training.model.steps.WorkoutStepResponse;
import com.gymbuddy.training.persistence.domain.WorkoutStep;
import com.gymbuddy.training.persistence.domain.WorkoutStepId;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper class for WorkoutStep DTO and Entity methods.
 */
@Mapper(componentModel = "spring", uses = WorkoutStepId.class)
public interface WorkoutStepsDataMapper {

    @Named("workoutStepDto")
    @Mapping(target = "stepId", source = "workoutStepId.stepId")
    WorkoutStepResponse toWorkoutStepDto(final WorkoutStep workoutStep);

    @IterableMapping(qualifiedByName = "workoutStepDto")
    List<WorkoutStepResponse> toWorkoutsDto(final List<WorkoutStep> workoutSteps);

    @Mapping(target = "workoutStepId.workoutId", source = "workoutId")
    @Mapping(target = "workoutStepId.stepId", source = "stepPosition")
    WorkoutStep toWorkoutStep(final ChangeWorkoutStepRequest creatableWorkoutStepDto,
                              final Long workoutId,
                              final Integer stepPosition);

    void modifyEntity(@MappingTarget final WorkoutStep workoutStepEntity,
                      final ChangeWorkoutStepRequest editableWorkoutStepDto);
}
