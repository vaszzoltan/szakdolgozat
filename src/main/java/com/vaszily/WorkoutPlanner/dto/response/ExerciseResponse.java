package com.vaszily.WorkoutPlanner.dto.response;


import com.vaszily.WorkoutPlanner.model.Exercise;

public class ExerciseResponse {
    private Long id;
    private String name;
    private Boolean repeatable;

    public ExerciseResponse(Exercise exercise){
        this.id = exercise.getId();
        this.name = exercise.getName();
        this.repeatable = exercise.getRepeatable();
    }
}
