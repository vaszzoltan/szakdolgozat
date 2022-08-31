package com.vaszily.WorkoutPlanner.dto.response;

import com.vaszily.WorkoutPlanner.model.WorkoutPlan;

import java.util.List;
import java.util.stream.Collectors;

public class WorkoutPlanResponse {
    private String name;
    private List<WorkoutResponse> workouts;
    private Integer rating;
    private Integer numberOfRating;
    private String description;
    private String comment;

    public WorkoutPlanResponse(WorkoutPlan workoutPlan){
        this.name = workoutPlan.getName();
        this.workouts = workoutPlan.getWorkouts().stream().map(WorkoutResponse::new).collect(Collectors.toList());
        this.rating = workoutPlan.getRating();
        this.numberOfRating = workoutPlan.getNumberOfRating();
        this.description = workoutPlan.getDescription();
        this.comment = workoutPlan.getComment();
    }

}
