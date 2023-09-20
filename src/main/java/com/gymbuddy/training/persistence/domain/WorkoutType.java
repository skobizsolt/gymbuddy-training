package com.gymbuddy.training.persistence.domain;

import lombok.Getter;

/**
 * Enum that represents the type of the workout.
 */
@Getter
public enum WorkoutType {
    TIME_BASED("Interval"),
    REP_BASED("Rep Count");

    final String name;

    WorkoutType(String name) {
        this.name = name;
    }
}
