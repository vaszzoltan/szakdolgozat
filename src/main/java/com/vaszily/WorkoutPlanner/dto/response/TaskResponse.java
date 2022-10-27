package com.vaszily.WorkoutPlanner.dto.response;

import com.vaszily.WorkoutPlanner.model.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
public class TaskResponse {
    private Long id;
    private String name;
    private List<ExerciseWrapperResponse> exerciseResponses;
    private String comment;
    //private Boolean done;

    public TaskResponse(Task task){
        this.id = task.getId();
        this.name = task.getName();
        this.exerciseResponses = task.getExerciseWrappers().stream().map(ExerciseWrapperResponse::new).collect(Collectors.toList());
        this.comment = task.getComment();
        //this.done = task.getIsDone();
    }
}
