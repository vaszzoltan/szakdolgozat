package com.vaszily.WorkoutPlanner.model;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Account extends BaseEntity {
    private List<WorkoutPlan> workoutPlanList;



}
