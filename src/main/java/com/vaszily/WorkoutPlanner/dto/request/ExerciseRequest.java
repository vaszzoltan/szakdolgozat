package com.vaszily.WorkoutPlanner.dto.request;

import com.vaszily.WorkoutPlanner.model.Exercise;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ExerciseRequest {
    private Long id;
    @NotBlank(message = "Exercise's name cannot be null or empty")
    private String name;
    @NotNull(message = "Exercise's isRepeatable cannot be null!")
    private Boolean isRepeatable;

    public Exercise asEntity(){
        Exercise exercise = new Exercise();
        exercise.setName(this.name);
        exercise.setRepeatable(this.isRepeatable);
        return exercise;
    }
}
