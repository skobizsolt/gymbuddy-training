package com.gymbuddy.training.persistence.repository;

import com.gymbuddy.training.persistence.domain.WorkoutStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link WorkoutStep}.
 */
@Repository
public interface WorkoutStepsRepository extends JpaRepository<WorkoutStep, Long> {
}