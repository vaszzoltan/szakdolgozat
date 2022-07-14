package com.vaszily.WorkoutPlanner.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Exercise extends BaseEntity{
    private Integer reps;
    private Integer sets;
    private Integer usedWeight;
    private String comment;
    private Boolean done;
}
