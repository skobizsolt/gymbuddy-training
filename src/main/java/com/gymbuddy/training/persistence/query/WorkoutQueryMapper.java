package com.gymbuddy.training.persistence.query;

import com.gymbuddy.training.persistence.domain.QWorkout;
import com.gymbuddy.training.persistence.domain.Workout;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Query mapper for {@link Workout} queries.
 */
@Component
@RequiredArgsConstructor
public class WorkoutQueryMapper implements BaseQueryMapper<Workout> {

    @PersistenceContext
    private EntityManager entityManager;

    private static final QWorkout WORKOUT = QWorkout.workout;

    @Override
    public List<Workout> getAllRecords() {
        final JPAQuery<Workout> query = new JPAQuery<>(entityManager);
        return query.from(WORKOUT).fetch();
    }

    @Override
    public Optional<Workout> getRecordById(Long id) {
        final JPAQuery<Workout> query = new JPAQuery<>(entityManager);
        final Workout workout =
                query.from(WORKOUT).where(WORKOUT.workoutId.eq(id)).fetchFirst();
        return Optional.ofNullable(workout);
    }
}
