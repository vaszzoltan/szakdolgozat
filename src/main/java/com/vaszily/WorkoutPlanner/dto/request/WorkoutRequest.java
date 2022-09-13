package com.vaszily.WorkoutPlanner.dto.request;

import com.vaszily.WorkoutPlanner.model.auth.Account;
import com.vaszily.WorkoutPlanner.model.Task;
import com.vaszily.WorkoutPlanner.model.Workout;
import com.vaszily.WorkoutPlanner.model.WorkoutPlan;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
@Getter
@Setter
public class WorkoutRequest {
    private String name;
    private Set<Long> tasks;
    private Long workoutPlan;
    private String account;
    private String goalDate;
    private String finishDate;
    private String description;
    private String afterEffect;

    public Workout asEntity(){
        Workout workout = new Workout();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        workout.setName(name);
        workout.setTasks(tasks.stream().map(Task::new).collect(Collectors.toList()));
        workout.setWorkoutPlans(Arrays.asList(new WorkoutPlan(workoutPlan)));
        workout.setAccounts(Arrays.asList(new Account(account)));
        try {
            workout.setGoalDate(sdf.parse(goalDate));
            workout.setFinishDate(sdf.parse(finishDate));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("Wrong date format!");
        }
        workout.setDescription(description);
        workout.setAfterEffect(afterEffect);
        return workout;
    }
}
