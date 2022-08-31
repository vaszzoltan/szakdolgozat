package com.vaszily.WorkoutPlanner.dto.response;

import com.vaszily.WorkoutPlanner.model.Task;

import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

public class TaskResponse {
    private String name;
    @OneToMany(mappedBy ="task")
    private List<ExerciseResponse> exerciseResponses;
    private String comment;
    private Boolean done;

    public TaskResponse(Task task){
        this.name = task.getName();
        this.exerciseResponses = task.getExerciseWrappers().stream().map(ExerciseResponse::new).collect(Collectors.toList());
        this.comment = task.getComment();
        this.done = task.getDone();
    }
}
