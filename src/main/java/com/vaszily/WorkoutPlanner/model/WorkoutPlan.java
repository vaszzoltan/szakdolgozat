package com.vaszily.WorkoutPlanner.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class WorkoutPlan extends BaseEntity {
    private String name;
    @ManyToMany(mappedBy ="workoutPlans")
    private List<Workout> workouts;
    @ManyToMany
    private List<Account> accounts;
    private Integer rating;
    private Integer numberOfRating;
    private String description;
    private String comment;
}
