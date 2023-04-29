package com.gymbuddy.training.persistence.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * Entity class that represents steps of the created workout.
 */
@Entity(name = "workout_step")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkoutStep {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id Long stepId;
    @NotNull String stepName;
    String details;
    @Enumerated(value = EnumType.STRING)
    @NotNull WorkoutType workoutType;
    @ManyToOne(targetEntity = Workout.class)
    @JoinColumn(name = "workout_id")
    @NotNull Workout workout;
}
