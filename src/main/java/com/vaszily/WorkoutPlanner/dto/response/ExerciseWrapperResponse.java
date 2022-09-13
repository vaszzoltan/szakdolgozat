package com.vaszily.WorkoutPlanner.dto.response;

import com.vaszily.WorkoutPlanner.model.ExerciseWrapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseWrapperResponse {
    private String name;
    private Boolean repeatable;
    private Integer reps;
    private Integer sets;
    private Integer durationInSecond;
    private Integer usedWeight;

    public ExerciseWrapperResponse(ExerciseWrapper exerciseWrapper){
        this.name = exerciseWrapper.getExercise().getName();
        this.repeatable = exerciseWrapper.getExercise().getRepeatable();
        this.reps = exerciseWrapper.getReps();
        this.sets = exerciseWrapper.getSets();
        this.durationInSecond = exerciseWrapper.getDurationInSecond();
        this.usedWeight = exerciseWrapper.getUsedWeight();
    }
}
