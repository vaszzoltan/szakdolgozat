package com.vaszily.WorkoutPlanner.dto.request;

import com.vaszily.WorkoutPlanner.model.ExerciseWrapper;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ExerciseWrapperRequest {
    private ExerciseRequest exercise;
    @NotNull
    @Min(value = 0, message = "Min number of sets is 1!")
    private Integer sets;
    private Integer reps;
    private Integer durationInSecond;
    private Integer usedWeight;
    private TaskRequest task;

    public ExerciseWrapper asEntity(){
        ExerciseWrapper exerciseWrapper = new ExerciseWrapper();
        exerciseWrapper.setExercise(null);
        exerciseWrapper.setSets(this.sets);
        exerciseWrapper.setReps(this.reps);
        exerciseWrapper.setDurationInSecond(this.durationInSecond);
        exerciseWrapper.setUsedWeight(this.usedWeight);
        exerciseWrapper.setTask(null);
        return exerciseWrapper;
    }

}
