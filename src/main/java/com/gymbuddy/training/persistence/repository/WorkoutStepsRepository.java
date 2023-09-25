package com.gymbuddy.training.persistence.repository;

import com.gymbuddy.training.persistence.domain.WorkoutStep;
import jakarta.persistence.Tuple;
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
            " AND s.step_number = :stepNumber", nativeQuery = true)
    Optional<WorkoutStep> findWorkoutStepByWorkoutIdAndStepNumber(Long workoutId, Long stepNumber);

    @Query(value = "SELECT MAX(step_number) + 1 FROM workout_step s" +
            " WHERE s.workout_id = :workoutId", nativeQuery = true)
    Optional<Integer> getNextStepNumber(Long workoutId);

    @Query(value = "SELECT COUNT(workout_id) as totalSteps," +
            " SUM(estimated_time) as estimatedTimeInMinutes" +
            " FROM workout_step s" +
            " WHERE s.workout_id = :workoutId", nativeQuery = true)
    Tuple getGeneralDataByWorkoutId(Long workoutId);
}