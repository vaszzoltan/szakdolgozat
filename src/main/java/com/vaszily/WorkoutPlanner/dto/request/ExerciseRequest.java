package com.vaszily.WorkoutPlanner.dto.request;

import com.vaszily.WorkoutPlanner.model.Exercise;


public class ExerciseRequest {
    private Long id;
    private String name;
    private Boolean dynamic;

    public Exercise asEntity(){
        Exercise exercise = new Exercise();
        exercise.setName(name);
        exercise.setDynamic(dynamic);
        return exercise;
    }
}
