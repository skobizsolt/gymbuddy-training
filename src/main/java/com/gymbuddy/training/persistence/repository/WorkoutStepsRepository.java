package com.gymbuddy.training.persistence.repository;

import com.gymbuddy.training.persistence.domain.WorkoutStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for {@link WorkoutStep}.
 */
@Repository
public interface WorkoutStepsRepository extends JpaRepository<WorkoutStep, Long> {

    @Query(value = "SELECT * FROM workout_step s" +
            " WHERE s.workout_id = :workoutId", nativeQuery = true)
    List<WorkoutStep> findAllByWorkoutId(Long workoutId);

    @Query(value = "SELECT * FROM workout_step s" +
            " WHERE s.workout_id = :workoutId" +
            " AND s.step_id = :stepId", nativeQuery = true)
    Optional<WorkoutStep> findWorkoutStepByPrimaryKeys(Long workoutId, Long stepId);

    @Query(value = "SELECT MAX(step_position) + 1 FROM workout_step s" +
            " WHERE s.workout_id = :workoutId", nativeQuery = true)
    Optional<Integer> getNextStepPositionInWorkout(Long workoutId);
}