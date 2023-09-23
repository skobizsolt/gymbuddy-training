package com.gymbuddy.training.mapper;

import com.gymbuddy.training.dto.ChangeWorkoutRequest;
import com.gymbuddy.training.dto.WorkoutResponse;
import com.gymbuddy.training.persistence.domain.Workout;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Mapper class for Workout model and Entity methods.
 */
@Mapper(imports = LocalDateTime.class)
public interface WorkoutDataMapper {

    @Named("workoutDto")
    WorkoutResponse toWorkoutDto(final Workout workout);

    @IterableMapping(qualifiedByName = "workoutDto")
    List<WorkoutResponse> toWorkoutsDto(final List<Workout> workouts);

    @Mapping(target = "registeredOn", expression = "java(LocalDateTime.now())")
    @Mapping(target = "lastModified", expression = "java(LocalDateTime.now())")
    Workout toWorkout(final ChangeWorkoutRequest creatableWorkout, final String userId);

    @Mapping(target = "lastModified", expression = "java(LocalDateTime.now())")
    void modifyEntity(@MappingTarget Workout workoutEntity, ChangeWorkoutRequest updatableWorkout);
}
