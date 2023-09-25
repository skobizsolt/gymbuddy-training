package com.gymbuddy.training.service;

import com.gymbuddy.training.model.util.WorkoutDetailsResponse;
import com.gymbuddy.training.persistence.repository.WorkoutStepsRepository;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultWorkoutUtilService implements WorkoutUtilService {

    private final WorkoutStepsRepository workoutStepsRepository;


    @Override
    public WorkoutDetailsResponse getGeneralDetails(final Long workoutId) {
        Tuple queryResponse = workoutStepsRepository.getGeneralDataByWorkoutId(workoutId);

        if (queryResponse == null) {
            return WorkoutDetailsResponse.builder().build();
        }

        return WorkoutDetailsResponse
                .builder()
                .estimatedTimeInMinutes(
                        calculateEstimatedTime(
                                Optional.of((Long) queryResponse.get("estimatedTimeInMinutes".toLowerCase()))
                                        .orElse(0L)))
                .totalSteps(Optional.of((Long) queryResponse.get("totalSteps".toLowerCase()))
                        .orElse(0L))
                .build();
    }

    private Long calculateEstimatedTime(Long estimatedTime) {
        return estimatedTime / 60;
    }
}
