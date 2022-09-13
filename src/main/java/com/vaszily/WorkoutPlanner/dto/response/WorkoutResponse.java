package com.vaszily.WorkoutPlanner.dto.response;

import com.vaszily.WorkoutPlanner.model.Workout;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
public class WorkoutResponse {
    private String name;
    private List<TaskResponse> tasks;
    private Date goalDate;
    private Date finishDate;
    private String description;
    private String afterEffect;

    public WorkoutResponse(Workout workout){
        this.name = workout.getName();
        this.tasks = workout.getTasks().stream().map(TaskResponse::new).collect(Collectors.toList());
        this.goalDate = workout.getGoalDate();
        this.finishDate = workout.getFinishDate();
        this.description = workout.getDescription();
        this.afterEffect = workout.getAfterEffect();
    }

}
