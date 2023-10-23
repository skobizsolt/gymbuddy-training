package com.gymbuddy.training.service;

import com.gymbuddy.training.exception.Errors;
import com.gymbuddy.training.exception.ServiceExpection;
import com.gymbuddy.training.mapper.WorkoutDataMapper;
import com.gymbuddy.training.model.ChangeWorkoutRequest;
import com.gymbuddy.training.model.WorkoutResponse;
import com.gymbuddy.training.model.steps.WorkoutStepResponse;
import com.gymbuddy.training.persistence.domain.Workout;
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
    private final WorkoutDataMapper workoutDataMapper;
    private final WorkoutStepService workoutStepService;

    /**
     * {@inheritDoc}
     */
    @Override
    public WorkoutResponse getWorkout(final Long workoutId) {
        final Workout workout = getWorkoutById(workoutId);
        return workoutDataMapper.toWorkoutDto(workout);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WorkoutResponse createWorkout(final ChangeWorkoutRequest creatableWorkout,
                                         final String userId) {
        final Workout workoutEntity = workoutDataMapper.toWorkout(creatableWorkout, userId);

        workoutRepository.save(workoutEntity);
        return workoutDataMapper.toWorkoutDto(workoutEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WorkoutResponse editWorkout(final ChangeWorkoutRequest updatableWorkout,
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
        final List<WorkoutStepResponse> steps = workoutStepService.getAllSteps(workoutId);
        steps.forEach(step -> workoutStepService.deleteStep(workoutId, Long.valueOf(step.getStepPosition())));
        workoutRepository.delete(workout);

    }

    private Workout getWorkoutById(final Long workoutId) {
        return workoutRepository.findByWorkoutId(workoutId)
                .orElseThrow(() -> getWorkoutNotFoundException(workoutId));
    }

    private ServiceExpection getWorkoutNotFoundException(final Long workoutId) {
        return new ServiceExpection(Errors.WORKOUT_NOT_FOUND, "id: " + workoutId.toString());
    }
}
