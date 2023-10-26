package com.gymbuddy.training.service;

import com.gymbuddy.training.exception.ServiceExpection;
import com.gymbuddy.training.mapper.WorkoutStepsDataMapper;
import com.gymbuddy.training.model.steps.ChangeWorkoutStepRequest;
import com.gymbuddy.training.model.steps.WorkoutStepResponse;
import com.gymbuddy.training.persistence.domain.WorkoutStep;
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
    private final WorkoutStepsDataMapper workoutStepsDataMapper;

    @Override
    public List<WorkoutStepResponse> getAllSteps(final Long workoutId) {
        final List<WorkoutStep> workoutSteps = workoutStepsRepository.findAllByWorkoutId(workoutId);
        return workoutStepsDataMapper.toWorkoutsDto(workoutSteps);
    }

    @Override
    public WorkoutStepResponse getStep(final Long workoutId, final Long stepId) {
        final WorkoutStep workoutStep = getWorkoutStep(workoutId, stepId);
        return workoutStepsDataMapper.toWorkoutStepDto(workoutStep);
    }

    @Override
    public WorkoutStepResponse addStep(final Long workoutId, final ChangeWorkoutStepRequest creatableWorkoutStepDto) {
        final Integer lastStepPosition = workoutStepsRepository.getNextStepPositionInWorkout(workoutId).orElse(1);
        final WorkoutStep workoutStep =
                workoutStepsDataMapper.toWorkoutStep(creatableWorkoutStepDto, workoutId, lastStepPosition);
        log.info("Creating: WorkoutStep::Step: {}", workoutStep.getWorkoutStepId());
        workoutStepsRepository.save(workoutStep);
        log.info("Created: WorkoutStep::Step: {}", workoutStep.getWorkoutStepId());
        return workoutStepsDataMapper.toWorkoutStepDto(workoutStep);
    }

    @Override
    public WorkoutStepResponse editStep(final Long workoutId, final Long stepId, final ChangeWorkoutStepRequest editableWorkoutStepDto) {
        final WorkoutStep workoutStep = getWorkoutStep(workoutId, stepId);
        log.info("Editing: WorkoutStep::Step: {}", workoutStep.getWorkoutStepId());
        workoutStepsDataMapper.modifyEntity(workoutStep, editableWorkoutStepDto);
        workoutStepsRepository.save(workoutStep);
        log.info("Edit successful! WorkoutStep::Step: {}", workoutStep.getWorkoutStepId());
        return workoutStepsDataMapper.toWorkoutStepDto(workoutStep);
    }

    @Override
    public void deleteStep(final Long workoutId, final Long stepId) {
        final WorkoutStep workoutStep = getWorkoutStep(workoutId, stepId);
        log.debug("Deleting: WorkoutStep::Step: {}", stepId);
        workoutStepsRepository.delete(workoutStep);
    }

    private WorkoutStep getWorkoutStep(final Long workoutId,
                                       final Long stepId) {
        return workoutStepsRepository.findWorkoutStepByPrimaryKeys(workoutId, stepId)
                .orElseThrow(() -> new ServiceExpection(WORKOUT_STEP_NOT_FOUND, "Step: " + stepId));
    }
}
