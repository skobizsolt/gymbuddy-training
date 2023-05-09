package com.gymbuddy.training.persistence.query;

import com.gymbuddy.training.persistence.domain.QWorkoutStep;
import com.gymbuddy.training.persistence.domain.WorkoutStep;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Query mapper for {@link WorkoutStep} queries.
 */
@Component
@RequiredArgsConstructor
public class WorkoutStepQueryMapper {

    @PersistenceContext
    private EntityManager entityManager;

    private static final QWorkoutStep WORKOUT_STEP = QWorkoutStep.workoutStep;

    public List<WorkoutStep> getAllStepsForWorkout(final Long workoutId) {
        final JPAQuery<WorkoutStep> query = new JPAQuery<>(entityManager);
        return query.from(WORKOUT_STEP)
                .where(WORKOUT_STEP.workoutStepId.workoutId.eq(workoutId))
                .orderBy(WORKOUT_STEP.workoutStepId.stepId.asc())
                .fetch();
    }

    public Optional<WorkoutStep> getWorkoutStepByWorkoutIdAndStep(final Long workoutId, final Long stepNumber) {
        final JPAQuery<WorkoutStep> query = new JPAQuery<>(entityManager);
        final WorkoutStep workoutStep =
                query.from(WORKOUT_STEP)
                        .where(
                                WORKOUT_STEP.workoutStepId.workoutId.eq(workoutId)
                                        .and(WORKOUT_STEP.workoutStepId.stepId.eq(stepNumber)))
                        .fetchFirst();
        return Optional.ofNullable(workoutStep);
    }

    public Integer getLastStep(final Long workoutId) {
        final JPAQuery<WorkoutStep> query = new JPAQuery<>(entityManager);
        final WorkoutStep workoutStep = query.from(WORKOUT_STEP)
                .where(WORKOUT_STEP.workoutStepId.workoutId.eq(workoutId))
                .orderBy(WORKOUT_STEP.stepNumber.desc())
                .fetchFirst();
        return workoutStep != null ? workoutStep.getStepNumber() : null;
    }

}
