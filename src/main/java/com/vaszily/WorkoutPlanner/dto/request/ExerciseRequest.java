package com.vaszily.WorkoutPlanner.dto.request;

import com.vaszily.WorkoutPlanner.model.Exercise;


public class ExerciseRequest {
    private Long id;
    private String name;
    private Boolean repeatable;

    public Exercise asEntity(){
        Exercise exercise = new Exercise();
        exercise.setName(name);
        exercise.setRepeatable(repeatable);
        return exercise;
    }
}
