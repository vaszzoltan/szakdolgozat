package com.vaszily.WorkoutPlanner.dto.response;

import com.vaszily.WorkoutPlanner.model.ExerciseWrapper;

public class ExerciseResponse {
    String name;
    Boolean repeatable;
    Integer reps;
    Integer sets;
    Integer durationInSecond;
    Integer usedWeight;

    public ExerciseResponse(ExerciseWrapper exerciseWrapper){
        this.name = exerciseWrapper.getExercise().getName();
        this.repeatable = exerciseWrapper.getExercise().getRepeatable();
        this.reps = exerciseWrapper.getReps();
        this.sets = exerciseWrapper.getSets();
        this.durationInSecond = exerciseWrapper.getDurationInSecond();
        this.usedWeight = exerciseWrapper.getUsedWeight();
    }
}
