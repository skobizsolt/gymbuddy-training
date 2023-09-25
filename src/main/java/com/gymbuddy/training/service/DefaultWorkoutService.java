package com.gymbuddy.training.service;

import com.gymbuddy.training.exception.Errors;
import com.gymbuddy.training.exception.ServiceExpection;
import com.gymbuddy.training.mapper.WorkoutDataMapper;
import com.gymbuddy.training.model.*;
import com.gymbuddy.training.model.steps.ChangeWorkoutStepRequest;
import com.gymbuddy.training.model.steps.WorkoutStepResponse;
import com.gymbuddy.training.persistence.domain.Workout;
import com.gymbuddy.training.persistence.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    public WorkoutListResponse getAllWorkouts() {
        final List<Workout> workouts = workoutRepository.findAll();
        final List<WorkoutResponse> workoutResponses = workoutDataMapper.toWorkoutsDto(workouts);
        return WorkoutListResponse.builder()
                .workouts(workoutResponses).build();
    }

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
    public DetailedWorkoutsResponse createWorkout(final CreateWorkoutRequest creatableWorkout,
                                                  final String userId) {
        final Workout workoutEntity = workoutDataMapper.toWorkout(creatableWorkout, userId);

        workoutRepository.save(workoutEntity);

        final List<WorkoutStepResponse> savedSteps = saveSteps(creatableWorkout.getSteps(), workoutEntity.getWorkoutId());
        final WorkoutResponse workoutResponse = workoutDataMapper.toWorkoutDto(workoutEntity);
        return DetailedWorkoutsResponse.builder()
                .workout(workoutResponse)
                .steps(savedSteps).build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WorkoutResponse editWorkout(final EditWorkoutRequest updatableWorkout,
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
        return workoutRepository.findByWorkoutId(workoutId)
                .orElseThrow(() -> getWorkoutNotFoundException(workoutId));
    }

    private ServiceExpection getWorkoutNotFoundException(final Long workoutId) {
        return new ServiceExpection(Errors.WORKOUT_NOT_FOUND, "id: " + workoutId.toString());
    }

    private List<WorkoutStepResponse> saveSteps(List<ChangeWorkoutStepRequest> steps, Long workoutId) {
        if (!steps.isEmpty()) {
            return steps
                    .stream()
                    .map(changeWorkoutStepDto ->
                            workoutStepService.addStep(workoutId, changeWorkoutStepDto))
                    .toList();
        } else {
            return Collections.emptyList();
        }
    }
}
