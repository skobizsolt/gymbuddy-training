package com.gymbuddy.training.service;

import com.gymbuddy.training.mapper.WorkoutDataMapper;
import com.gymbuddy.training.model.WorkoutListResponse;
import com.gymbuddy.training.persistence.domain.Workout;
import com.gymbuddy.training.persistence.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default implementation of {@link WorkoutListService}.
 */
@Service
@RequiredArgsConstructor
public class DefaultWorkoutListService implements WorkoutListService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutDataMapper workoutDataMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public WorkoutListResponse getAllWorkouts() {
        final List<Workout> workouts = workoutRepository.findAll();
        return WorkoutListResponse.builder().workouts(workoutDataMapper.toWorkoutsDto(workouts)).build();
    }
}
