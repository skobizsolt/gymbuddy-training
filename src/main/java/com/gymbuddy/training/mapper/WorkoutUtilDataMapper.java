package com.gymbuddy.training.mapper;

import com.gymbuddy.training.model.util.WorkoutDetailsResponse;
import com.gymbuddy.training.persistence.query.dto.GeneralStepDataDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkoutUtilDataMapper {

    WorkoutDetailsResponse toResponse(GeneralStepDataDto queryResponse);
}
