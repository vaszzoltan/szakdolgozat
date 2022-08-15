package com.vaszily.WorkoutPlanner.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Workout extends BaseEntity{
    private String name;
    @OneToMany(mappedBy ="workout")
    private List<Task> tasks;
    @ManyToMany
    private List<WorkoutPlan> workoutPlans;
    @ManyToMany
    private List<Account> accounts;
    private Date goalDate;
    private Date finishDate;
    private String description;
    private String afterEffect;
}
