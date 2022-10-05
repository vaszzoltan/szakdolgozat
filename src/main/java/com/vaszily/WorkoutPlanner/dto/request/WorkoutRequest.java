package com.vaszily.WorkoutPlanner.dto.request;

import com.vaszily.WorkoutPlanner.exception.DataUploadException;
import com.vaszily.WorkoutPlanner.model.Task;
import com.vaszily.WorkoutPlanner.model.Workout;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
@Getter
@Setter
public class WorkoutRequest {
    @NotBlank(message = "Workout's name cannot be null or empty!")
    private String name;
    @NotNull(message = "Tasks cannot be null!")
    private Set<Long> tasks;
    @Pattern(regexp = "((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")
    private String goalDate;
    @Pattern(regexp = "((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")
    private String finishDate;
    private String description;
    private String afterEffect;

    public Workout asEntity(){
        Workout workout = new Workout();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        workout.setName(name);
        if(tasks.size()==0)
            throw new DataUploadException("Tasks size cannot be less than 1!");
        workout.setTasks(tasks.stream().map(Task::new).collect(Collectors.toList()));
        workout.setWorkoutPlans(new HashSet<>());
        workout.setAccounts(new ArrayList<>());
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
