package com.gymbuddy.training.service;

import com.gymbuddy.training.dto.ChangeWorkoutDto;
import com.gymbuddy.training.dto.DetailedWorkoutsDto;
import com.gymbuddy.training.dto.WorkoutDto;
import com.gymbuddy.training.dto.WorkoutsDto;
import com.gymbuddy.training.dto.steps.ChangeWorkoutStepDto;
import com.gymbuddy.training.dto.steps.GeneralStepDetailsDto;
import com.gymbuddy.training.dto.steps.WorkoutStepDto;
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
    public WorkoutsDto getAllWorkouts() {
        final List<Workout> workouts = workoutQueryMapper.getAllRecords();
        final List<GeneralStepDetailsDto> generalDetails = workouts
                .stream()
                .map(workout -> workoutStepService.getGeneralStepDetails(workout.getWorkoutId()))
                .toList();
        final List<WorkoutDto> workoutDtos = workoutDataMapper.toWorkoutsDto(workouts);
        mapGeneralDetailsToList(workoutDtos, generalDetails);
        return WorkoutsDto.builder()
                .workouts(workoutDtos).build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WorkoutDto getWorkout(final Long workoutId) {
        final Workout workout = getWorkoutById(workoutId);
        return getWorkoutDto(workout, workoutId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DetailedWorkoutsDto createWorkout(final ChangeWorkoutDto creatableWorkout,
                                             final String userId) {
        final Workout workoutEntity = workoutDataMapper.toWorkout(creatableWorkout, userId);

        workoutRepository.save(workoutEntity);

        final List<WorkoutStepDto> savedSteps = saveSteps(creatableWorkout.getSteps(), workoutEntity.getWorkoutId());
        final WorkoutDto workoutDto = getWorkoutDto(workoutEntity, workoutEntity.getWorkoutId());
        return DetailedWorkoutsDto.builder()
                .workout(workoutDto)
                .steps(savedSteps).build();
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
        final WorkoutDto workoutDto = workoutDataMapper.toWorkoutDto(workoutEntity);
        workoutStepService.getGeneralStepDetails(workoutEntity.getWorkoutId());
        return workoutDto;
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

    private List<WorkoutStepDto> saveSteps(List<ChangeWorkoutStepDto> steps, Long workoutId) {
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

    private WorkoutDto getWorkoutDto(Workout workoutEntity, Long workoutId) {
        final WorkoutDto workoutDto = workoutDataMapper.toWorkoutDto(workoutEntity);
        workoutDto.setStepDetails(workoutStepService.getGeneralStepDetails(workoutId));
        return workoutDto;
    }

    private void mapGeneralDetailsToList(List<WorkoutDto> workoutDtos, List<GeneralStepDetailsDto> generalDetails) {

        IntStream.range(0, workoutDtos.size())
                .forEach(i -> workoutDtos.get(i).setStepDetails(generalDetails.get(i)));
    }
}
