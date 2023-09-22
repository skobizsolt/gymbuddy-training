package com.gymbuddy.training.mapper;

import com.gymbuddy.training.dto.ChangeWorkoutDto;
import com.gymbuddy.training.dto.WorkoutDto;
import com.gymbuddy.training.persistence.domain.Workout;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Mapper class for Workout model and Entity methods.
 */
@Mapper(imports = {LocalDateTime.class, WorkoutStepsDataMapper.class})
public interface WorkoutDataMapper {

    @Named("workoutDto")
    WorkoutDto toWorkoutDto(final Workout workout);

    @IterableMapping(qualifiedByName = "workoutDto")
    List<WorkoutDto> toWorkoutsDto(final List<Workout> workouts);

    @Mapping(target = "registeredOn", expression = "java(LocalDateTime.now())")
    @Mapping(target = "lastModified", expression = "java(LocalDateTime.now())")
    Workout toWorkout(final ChangeWorkoutDto creatableWorkout, final Long userId);

    @Mapping(target = "lastModified", expression = "java(LocalDateTime.now())")
    void modifyEntity(@MappingTarget Workout workoutEntity, ChangeWorkoutDto updatableWorkout);
}
