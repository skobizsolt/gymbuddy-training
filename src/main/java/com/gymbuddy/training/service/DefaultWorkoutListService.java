package com.gymbuddy.training.service;

import com.gymbuddy.training.mapper.WorkoutDataMapper;
import com.gymbuddy.training.model.WorkoutListResponse;
import com.gymbuddy.training.model.WorkoutResponse;
import com.gymbuddy.training.persistence.domain.Workout;
import com.gymbuddy.training.persistence.domain.WorkoutCategory;
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
    public WorkoutListResponse getAllWorkouts(final WorkoutCategory category) {
        if (category != null) {
            return getAllWorkoutsByCategory(category);
        }
        final List<Workout> workouts = workoutRepository.findAll();
        return getWorkoutListResponse(workouts);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WorkoutListResponse getAllWorkoutsByUserId(final String userId) {
        final List<Workout> workouts = workoutRepository.findAllByUserId(userId);
        return getWorkoutListResponse(workouts);
    }

    private WorkoutListResponse getAllWorkoutsByCategory(final WorkoutCategory category) {
        final List<Workout> workouts = workoutRepository.findAllByCategory(category);
        return getWorkoutListResponse(workouts);
    }

    private WorkoutListResponse getWorkoutListResponse(List<Workout> workouts) {
        final List<WorkoutResponse> workoutResponses = workoutDataMapper.toWorkoutsDto(workouts);
        return WorkoutListResponse.builder()
                .workouts(workoutResponses).build();
    }
}
