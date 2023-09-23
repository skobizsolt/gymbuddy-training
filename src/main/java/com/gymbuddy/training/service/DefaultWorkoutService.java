package com.gymbuddy.training.service;

import com.gymbuddy.training.dto.ChangeWorkoutRequest;
import com.gymbuddy.training.dto.DetailedWorkoutsResponse;
import com.gymbuddy.training.dto.WorkoutListResponse;
import com.gymbuddy.training.dto.WorkoutResponse;
import com.gymbuddy.training.dto.steps.ChangeWorkoutStepRequest;
import com.gymbuddy.training.dto.steps.GeneralStepDetailsDto;
import com.gymbuddy.training.dto.steps.WorkoutStepResponse;
import com.gymbuddy.training.exception.Errors;
import com.gymbuddy.training.exception.ServiceExpection;
import com.gymbuddy.training.mapper.WorkoutDataMapper;
import com.gymbuddy.training.persistence.domain.Workout;
import com.gymbuddy.training.persistence.query.WorkoutQueryMapper;
import com.gymbuddy.training.persistence.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Default implementation of {@link WorkoutService}.
 */
@Service
@RequiredArgsConstructor
public class DefaultWorkoutService implements WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutQueryMapper workoutQueryMapper;
    private final WorkoutDataMapper workoutDataMapper;
    private final WorkoutStepService workoutStepService;

    /**
     * {@inheritDoc}
     */
    @Override
    public WorkoutListResponse getAllWorkouts() {
        final List<Workout> workouts = workoutQueryMapper.getAllRecords();
        final List<GeneralStepDetailsDto> generalDetails = workouts
                .stream()
                .map(workout -> workoutStepService.getGeneralStepDetails(workout.getWorkoutId()))
                .toList();
        final List<WorkoutResponse> workoutResponses = workoutDataMapper.toWorkoutsDto(workouts);
        mapGeneralDetailsToList(workoutResponses, generalDetails);
        return WorkoutListResponse.builder()
                .workouts(workoutResponses).build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WorkoutResponse getWorkout(final Long workoutId) {
        final Workout workout = getWorkoutById(workoutId);
        return getWorkoutDto(workout, workoutId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DetailedWorkoutsResponse createWorkout(final ChangeWorkoutRequest creatableWorkout,
                                                  final String userId) {
        final Workout workoutEntity = workoutDataMapper.toWorkout(creatableWorkout, userId);

        workoutRepository.save(workoutEntity);

        final List<WorkoutStepResponse> savedSteps = saveSteps(creatableWorkout.getSteps(), workoutEntity.getWorkoutId());
        final WorkoutResponse workoutResponse = getWorkoutDto(workoutEntity, workoutEntity.getWorkoutId());
        return DetailedWorkoutsResponse.builder()
                .workout(workoutResponse)
                .steps(savedSteps).build();
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
        final WorkoutResponse workoutResponse = workoutDataMapper.toWorkoutDto(workoutEntity);
        workoutStepService.getGeneralStepDetails(workoutEntity.getWorkoutId());
        return workoutResponse;
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

    private WorkoutResponse getWorkoutDto(Workout workoutEntity, Long workoutId) {
        final WorkoutResponse workoutResponse = workoutDataMapper.toWorkoutDto(workoutEntity);
        workoutResponse.setStepDetails(workoutStepService.getGeneralStepDetails(workoutId));
        return workoutResponse;
    }

    private void mapGeneralDetailsToList(List<WorkoutResponse> workoutResponses, List<GeneralStepDetailsDto> generalDetails) {

        IntStream.range(0, workoutResponses.size())
                .forEach(i -> workoutResponses.get(i).setStepDetails(generalDetails.get(i)));
    }
}
