package com.vaszily.WorkoutPlanner.dto.request;

import com.vaszily.WorkoutPlanner.model.Workout;
import com.vaszily.WorkoutPlanner.model.WorkoutPlan;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class WorkoutPlanRequest {
    private String name;
    private List<Long> workouts;
    //private String account;
    //private Integer rating;
    private String description;
    private String comment;

    public WorkoutPlan asEntity(){
        WorkoutPlan workoutPlan = new WorkoutPlan();
        workoutPlan.setName(name);
        workoutPlan.setWorkouts(workouts.stream().map(Workout::new).collect(Collectors.toList()));
        //workoutPlan.setAccounts(Arrays.asList(new Account(account)));
        //workoutPlan.setRating(rating);
        workoutPlan.setDescription(description);
        workoutPlan.setComment(comment);
        return workoutPlan;
    }
}
