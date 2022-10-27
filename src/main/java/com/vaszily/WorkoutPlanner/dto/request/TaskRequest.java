package com.vaszily.WorkoutPlanner.dto.request;

import com.vaszily.WorkoutPlanner.exception.DataUploadException;
import com.vaszily.WorkoutPlanner.model.ExerciseWrapper;
import com.vaszily.WorkoutPlanner.model.Task;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;
@Getter
@Setter

public class TaskRequest {
    @NotBlank(message = "Task name cannot be null or emppty!")
    private String name;
    @NotNull(message = "Exercises wrappers cannot be null!")
    private Set<Long> exerciseWrappers;
    private String comment;
    //@NotNull
    //private Boolean done;

    public Task asEntity(Principal principal){
        Task task = new Task();
        task.setName(this.name);
        if(exerciseWrappers.size()==0)
            throw new DataUploadException("Exercise wrappers size cannot be less than 1!");
        task.setExerciseWrappers(exerciseWrappers.stream().map(ExerciseWrapper::new).collect(Collectors.toList()));
        //task.setWorkout(new Workout(workout));
        task.setWorkout(null);
        task.setComment(comment);
        //task.setIsDone(done);
        task.setCreatedBy(principal.getName());
        return task;
    }

    public Task asEntity(){
        Task task = new Task();
        task.setName(this.name);
        if(exerciseWrappers.size()==0)
            throw new DataUploadException("Exercise wrappers size cannot be less than 1!");
        task.setExerciseWrappers(exerciseWrappers.stream().map(ExerciseWrapper::new).collect(Collectors.toList()));
        //task.setWorkout(new Workout(workout));
        task.setWorkout(null);
        task.setComment(comment);
        //task.setIsDone(done);
        return task;
    }


}
