package com.gymbuddy.training.persistence.repository;

import com.gymbuddy.training.persistence.domain.Workout;
import com.gymbuddy.training.persistence.domain.WorkoutCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for {@link Workout}.
 */
@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    Optional<Workout> findByWorkoutId(Long workoutId);

    List<Workout> findAllByCategory(WorkoutCategory category);

    List<Workout> findAllByUserId(String userId);
}
