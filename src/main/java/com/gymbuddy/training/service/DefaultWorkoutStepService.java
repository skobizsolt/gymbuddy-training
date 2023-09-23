package com.gymbuddy.training.service;

import com.gymbuddy.training.dto.steps.ChangeWorkoutStepRequest;
import com.gymbuddy.training.dto.steps.GeneralStepDetailsDto;
import com.gymbuddy.training.dto.steps.WorkoutStepResponse;
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
    public List<WorkoutStepResponse> getAllSteps(final Long workoutId) {
        final List<WorkoutStep> workoutSteps = workoutStepQueryMapper.getAllStepsForWorkout(workoutId);
        return workoutStepsDataMapper.toWorkoutsDto(workoutSteps);
    }

    @Override
    public WorkoutStepResponse getStep(final Long workoutId, final Long stepNumber) {
        final WorkoutStep workoutStep = getWorkoutStep(workoutId, stepNumber);
        return workoutStepsDataMapper.toWorkoutStepDto(workoutStep);
    }

    @Override
    public WorkoutStepResponse addStep(final Long workoutId, final ChangeWorkoutStepRequest creatableWorkoutStepDto) {
        final Integer stepNumber = getStepCount(workoutId);
        final WorkoutStep workoutStep =
                workoutStepsDataMapper.toWorkoutStep(creatableWorkoutStepDto, workoutId, stepNumber);
        log.info("Creating: WorkoutStep::Step: {}", workoutStep.getWorkoutStepId());
        workoutStepsRepository.save(workoutStep);
        log.info("Created: WorkoutStep::Step: {}", workoutStep.getWorkoutStepId());
        return workoutStepsDataMapper.toWorkoutStepDto(workoutStep);
    }

    @Override
    public WorkoutStepResponse editStep(final Long workoutId, final Long stepNumber, final ChangeWorkoutStepRequest editableWorkoutStepDto) {
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

    @Override
    public GeneralStepDetailsDto getGeneralStepDetails(final Long workoutId) {
        final List<WorkoutStepResponse> steps = getAllSteps(workoutId);
        return GeneralStepDetailsDto.builder()
                .estimatedTimeInMinutes(calculateEstimatedTime(steps))
                .totalSteps(steps.size()).build();
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

    private Integer calculateEstimatedTime(List<WorkoutStepResponse> steps) {
        final List<Integer> stepDurations = steps.stream().map(WorkoutStepResponse::getEstimatedTime).toList();
        return stepDurations.isEmpty() ? 0 : stepDurations.stream().reduce(0, Integer::sum) / 60;
    }
}
