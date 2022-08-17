package com.vaszily.WorkoutPlanner.dto.request;

import com.vaszily.WorkoutPlanner.model.ExerciseWrapper;
import com.vaszily.WorkoutPlanner.model.Task;
import com.vaszily.WorkoutPlanner.model.Workout;
import java.util.Set;
import java.util.stream.Collectors;

public class TaskRequest {
    private String name;
    private Set<Long> exerciseWrappers;
    private Long workout;
    private String comment;
    private Boolean done;

    public Task asEntity(){
        Task task = new Task();
        task.setName(this.name);
        task.setExerciseWrappers(exerciseWrappers.stream().map(ExerciseWrapper::new).collect(Collectors.toList()));
        task.setWorkout(new Workout(workout));
        task.setComment(comment);
        task.setDone(done);
        return task;
    }


}
