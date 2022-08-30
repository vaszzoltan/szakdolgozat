package com.vaszily.WorkoutPlanner.dto.request;

import com.vaszily.WorkoutPlanner.model.Exercise;
import com.vaszily.WorkoutPlanner.model.ExerciseWrapper;
import com.vaszily.WorkoutPlanner.model.Task;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ExerciseWrapperRequest {
    private Long exercise;
    @NotNull
    @Min(value = 0, message = "Min number of sets is 1!")
    private Integer sets;
    private Integer reps;
    private Integer durationInSecond;
    private Integer usedWeight;
    private Long task;

    public ExerciseWrapper asEntity(){
        ExerciseWrapper exerciseWrapper = new ExerciseWrapper();
        exerciseWrapper.setExercise(new Exercise(exercise));
        exerciseWrapper.setSets(this.sets);
        exerciseWrapper.setReps(this.reps);
        exerciseWrapper.setDurationInSecond(this.durationInSecond);
        exerciseWrapper.setUsedWeight(this.usedWeight);
        exerciseWrapper.setTask(new Task(task));
        return exerciseWrapper;
    }

}
