package com.gymbuddy.training.service;

import com.gymbuddy.training.dto.steps.ChangeWorkoutStepDto;
import com.gymbuddy.training.dto.steps.WorkoutStepDto;
import com.gymbuddy.training.dto.steps.WorkoutStepsDto;
import com.gymbuddy.training.exception.ServiceExpection;
import com.gymbuddy.training.mapper.WorkoutStepsDataMapper;
import com.gymbuddy.training.persistence.domain.WorkoutStep;
import com.gymbuddy.training.persistence.query.WorkoutStepQueryMapper;
import com.gymbuddy.training.persistence.repository.WorkoutStepsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gymbuddy.training.exception.Errors.WORKOUT_STEP_NOT_FOUND;

/**
 * Default implementation of {@link WorkoutStepService}.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultWorkoutStepService implements WorkoutStepService {

    private final WorkoutStepsRepository workoutStepsRepository;
    private final WorkoutStepQueryMapper workoutStepQueryMapper;
    private final WorkoutStepsDataMapper workoutStepsDataMapper;

    @Override
    public WorkoutStepsDto getAllSteps(Long workoutId) {
        final List<WorkoutStep> workoutSteps = workoutStepQueryMapper.getAllStepsForWorkout(workoutId);
        final List<WorkoutStepDto> workoutStepsList = workoutStepsDataMapper.toWorkoutsDto(workoutSteps);
        return WorkoutStepsDto.builder().steps(workoutStepsList).build();
    }

    @Override
    public WorkoutStepDto getStep(final Long workoutId, final Long stepNumber) {
        final WorkoutStep workoutStep = getWorkoutStep(workoutId, stepNumber);
        return workoutStepsDataMapper.toWorkoutStepDto(workoutStep);
    }

    @Override
    public WorkoutStepDto addStep(Long workoutId, ChangeWorkoutStepDto creatableWorkoutStepDto) {
        final Integer stepNumber = getStepCount(workoutId);
        final WorkoutStep workoutStep =
                workoutStepsDataMapper.toWorkoutStep(creatableWorkoutStepDto, workoutId, stepNumber);
        log.info("Creating: WorkoutStep::Step: {}", workoutStep.getWorkoutStepId());
        workoutStepsRepository.save(workoutStep);
        log.info("Created: WorkoutStep::Step: {}", workoutStep.getWorkoutStepId());
        return workoutStepsDataMapper.toWorkoutStepDto(workoutStep);
    }



    @Override
    public WorkoutStepDto editStep(Long workoutId, Long stepNumber, ChangeWorkoutStepDto editableWorkoutStepDto) {
        final WorkoutStep workoutStep = getWorkoutStep(workoutId, stepNumber);
        log.info("Editing: WorkoutStep::Step: {}", workoutStep.getWorkoutStepId());
        workoutStepsDataMapper.modifyEntity(workoutStep, editableWorkoutStepDto);
        workoutStepsRepository.save(workoutStep);
        log.info("Edit successful! WorkoutStep::Step: {}", workoutStep.getWorkoutStepId());
        return workoutStepsDataMapper.toWorkoutStepDto(workoutStep);
    }

    @Override
    public void deleteStep(final Long workoutId, final Long stepNumber) {
        final WorkoutStep workoutStep = getWorkoutStep(workoutId, stepNumber);
        log.debug("Deleting: WorkoutStep::Step: {}", stepNumber);
        workoutStepsRepository.delete(workoutStep);
    }

    private WorkoutStep getWorkoutStep(final Long workoutId,
                                       final Long stepNumber) {
        return workoutStepQueryMapper.getWorkoutStepByWorkoutIdAndStep(workoutId, stepNumber)
                .orElseThrow(() -> new ServiceExpection(WORKOUT_STEP_NOT_FOUND, "Step: " + stepNumber));
    }

    private Integer getStepCount(final Long workoutId) {
        Integer stepCount = workoutStepQueryMapper.getLastStep(workoutId);
        if (stepCount == null) {
            return 1;
        }
        return stepCount + 1;
    }
}
