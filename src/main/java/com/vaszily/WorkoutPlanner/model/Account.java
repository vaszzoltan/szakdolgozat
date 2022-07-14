package com.vaszily.WorkoutPlanner.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
@Setter
public class Account extends BaseEntity {
    private List<WorkoutPlan> workoutPlanList;
    private List<Workout> workoutList;



}
