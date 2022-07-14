package com.vaszily.WorkoutPlanner.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
@Setter
public class Workout extends BaseEntity{
    private List<Exercise> exerciseList;
    private String description;
    private String afterEffect;
}
