package com.vaszily.WorkoutPlanner.dto.request;

import com.vaszily.WorkoutPlanner.exception.DataUploadException;
import com.vaszily.WorkoutPlanner.model.Workout;
import com.vaszily.WorkoutPlanner.model.WorkoutPlan;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class WorkoutPlanRequest {
    @NotBlank(message = "Workoutplan's name cannot be null or empty! ")
    private String name;
    @NotNull(message = "Workouts cannot be null!")
    private List<Long> workouts;
    //private String account;
    //private Integer rating;
    private String description;
    private String comment;

    public WorkoutPlan asEntity(Principal principal){
        WorkoutPlan workoutPlan = new WorkoutPlan();
        workoutPlan.setName(name);
        if(workouts.size()==0)
            throw new DataUploadException("Workouts size cannot be less than 1!");
        workoutPlan.setWorkouts(workouts.stream().map(Workout::new).collect(Collectors.toList()));
        //workoutPlan.setAccounts(Arrays.asList(new Account(account)));
        //workoutPlan.setRating(rating);
        workoutPlan.setDescription(description);
        workoutPlan.setComment(comment);
        workoutPlan.setCreatedBy(principal.getName());
        return workoutPlan;
    }

    public WorkoutPlan asEntity(){
        WorkoutPlan workoutPlan = new WorkoutPlan();
        workoutPlan.setName(name);
        if(workouts.size()==0)
            throw new DataUploadException("Workouts size cannot be less than 1!");
        workoutPlan.setWorkouts(workouts.stream().map(Workout::new).collect(Collectors.toList()));
        workoutPlan.setDescription(description);
        workoutPlan.setComment(comment);
        return workoutPlan;
    }
}
