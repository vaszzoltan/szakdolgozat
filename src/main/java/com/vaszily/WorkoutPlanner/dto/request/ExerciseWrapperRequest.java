package com.vaszily.WorkoutPlanner.dto.request;

import com.vaszily.WorkoutPlanner.model.Exercise;
import com.vaszily.WorkoutPlanner.model.ExerciseWrapper;
import com.vaszily.WorkoutPlanner.model.Task;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Getter
@Setter

public class ExerciseWrapperRequest {
    @NotNull
    @Min(value = 1, message = "Invalid exercise value!")
    private Long exercise;
    @NotNull
    @Min(value = 1, message = "Min number of sets is 1!")
    private Integer sets;
    @Min(value = 1, message = "Min value of reps is 1!")
    private Integer reps;
    @Min(value = 1, message = "Min value of duration in second is 1!")
    private Integer durationInSecond;
    @Min(value = 0, message = "Used weight cannot be less than 0!")
    private Integer usedWeight;
    //private Long task;

    public ExerciseWrapper asEntity(){
        ExerciseWrapper exerciseWrapper = new ExerciseWrapper();
        exerciseWrapper.setExercise(new Exercise(exercise));
        exerciseWrapper.setSets(this.sets);
        exerciseWrapper.setReps(this.reps);
        exerciseWrapper.setDurationInSecond(this.durationInSecond);
        exerciseWrapper.setUsedWeight(this.usedWeight);
        //exerciseWrapper.setTask(new Task(task));
        return exerciseWrapper;
    }

}
