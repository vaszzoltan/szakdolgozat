package com.vaszily.WorkoutPlanner.dto.request;

import com.vaszily.WorkoutPlanner.model.Exercise;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseRequest {
    private Long id;
    private String name;
    private Boolean repeatable;

    public Exercise asEntity(){
        Exercise exercise = new Exercise();
        exercise.setName(this.name);
        exercise.setRepeatable(this.repeatable);
        return exercise;
    }
}
