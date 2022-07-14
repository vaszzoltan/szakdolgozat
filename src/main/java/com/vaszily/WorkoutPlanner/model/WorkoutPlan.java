package com.vaszily.WorkoutPlanner.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
@Setter
public class WorkoutPlan extends BaseEntity {
    private List<Workout> workoutList;
    private Integer rating;
    private Integer numberOfRating;
    private String description;
    private String comment;
}
