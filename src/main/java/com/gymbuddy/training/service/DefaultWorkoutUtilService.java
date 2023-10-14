package com.gymbuddy.training.service;

import com.gymbuddy.training.mapper.WorkoutUtilDataMapper;
import com.gymbuddy.training.model.util.WorkoutDetailsResponse;
import com.gymbuddy.training.persistence.query.WorkoutQueryMapper;
import com.gymbuddy.training.persistence.query.dto.GeneralStepDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultWorkoutUtilService implements WorkoutUtilService {

    private final WorkoutQueryMapper workoutQueryMapper;
    private final WorkoutUtilDataMapper workoutUtilDataMapper;


    @Override
    public WorkoutDetailsResponse getGeneralDetails(final Long workoutId) {
        GeneralStepDataDto queryResponse = workoutQueryMapper.getGeneralData(workoutId);

        return workoutUtilDataMapper.toResponse(queryResponse);
    }
}
