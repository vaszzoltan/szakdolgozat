package com.vaszily.WorkoutPlanner.model;

import java.util.List;

public class Workout extends BaseEntity{
    private List<Exercise> exerciseList;
    private String description;
    private String afterEffect;
}
