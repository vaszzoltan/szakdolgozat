package com.vaszily.WorkoutPlanner.dto.request;

import com.vaszily.WorkoutPlanner.model.auth.Account;
import com.vaszily.WorkoutPlanner.model.Workout;
import com.vaszily.WorkoutPlanner.model.WorkoutPlan;
import java.util.Arrays;

public class WorkoutPlanRequest {
    private String name;
    private Long workout;
    private String account;
    private Integer rating;
    private String description;
    private String comment;

    public WorkoutPlan asEntity(){
        WorkoutPlan workoutPlan = new WorkoutPlan();
        workoutPlan.setName(name);
        workoutPlan.setWorkouts(Arrays.asList(new Workout(workout)));
        workoutPlan.setAccounts(Arrays.asList(new Account(account)));
        workoutPlan.setRating(rating);
        workoutPlan.setDescription(description);
        workoutPlan.setComment(comment);
        return workoutPlan;
    }
}
