package com.vaszily.WorkoutPlanner.model;

import java.util.List;

public class WorkoutPlan extends BaseEntity {
    private List<Workout> workoutList;
    private Integer rating;
    private Integer numberOfRating;
    private String description;
    private String comment;
}
