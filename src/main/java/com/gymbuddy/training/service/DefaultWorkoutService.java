package com.gymbuddy.training.service;

import com.gymbuddy.training.dto.ChangeWorkoutDto;
import com.gymbuddy.training.dto.WorkoutDto;
import com.gymbuddy.training.dto.WorkoutsDto;
import com.gymbuddy.training.exception.Errors;
import com.gymbuddy.training.exception.ServiceExpection;
import com.gymbuddy.training.mapper.WorkoutDataMapper;
import com.gymbuddy.training.persistence.domain.Workout;
import com.gymbuddy.training.persistence.query.WorkoutQueryMapper;
import com.gymbuddy.training.persistence.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default implementation of {@link WorkoutService}.
 */
@Service
@RequiredArgsConstructor
public class DefaultWorkoutService implements WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutQueryMapper workoutQueryMapper;
    private final WorkoutDataMapper workoutDataMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public WorkoutsDto getAllWorkouts() {
        final List<Workout> workouts = workoutQueryMapper.getAllRecords();
        return WorkoutsDto.builder()
                .workouts(workoutDataMapper.toWorkoutsDto(workouts)).build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WorkoutDto getWorkout(final Long workoutId) {
        final Workout workout = getWorkoutById(workoutId);
        return workoutDataMapper.toWorkoutDto(workout);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WorkoutDto createWorkout(final ChangeWorkoutDto creatableWorkout,
                                    final Long userId) {
        final Workout workoutEntity = workoutDataMapper.toWorkout(creatableWorkout, userId);
        workoutRepository.save(workoutEntity);
        return workoutDataMapper.toWorkoutDto(workoutEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WorkoutDto editWorkout(final ChangeWorkoutDto updatableWorkout,
                                  final Long workoutId) {
        final Workout workoutEntity = getWorkoutById(workoutId);
        workoutDataMapper.modifyEntity(workoutEntity, updatableWorkout);
        workoutRepository.save(workoutEntity);
        return workoutDataMapper.toWorkoutDto(workoutEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteWorkout(final Long workoutId) {
        final Workout workout = getWorkoutById(workoutId);
        workoutRepository.delete(workout);

    }

    private Workout getWorkoutById(final Long workoutId) {
        return workoutQueryMapper.getRecordById(workoutId)
                .orElseThrow(() -> getWorkoutNotFoundException(workoutId));
    }

    private ServiceExpection getWorkoutNotFoundException(final Long workoutId) {
        return new ServiceExpection(Errors.WORKOUT_NOT_FOUND, "id: " + workoutId.toString());
    }
}
