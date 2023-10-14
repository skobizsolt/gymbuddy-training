package com.gymbuddy.training.persistence.query;

import com.gymbuddy.training.persistence.query.dto.GeneralStepDataDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface WorkoutQueryMapper {

    GeneralStepDataDto getGeneralData(@Param("workoutId") final Long workoutId);
}
