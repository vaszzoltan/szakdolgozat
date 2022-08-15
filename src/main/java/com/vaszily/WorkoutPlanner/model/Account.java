package com.vaszily.WorkoutPlanner.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Account extends BaseEntity {
    private String name;
    // TODO private String role; enummal megoldani
    @ManyToMany(mappedBy = "accounts")
    private List<WorkoutPlan> workoutPlans;
    @ManyToMany(mappedBy = "accounts")
    private List<Workout> workouts;



}
