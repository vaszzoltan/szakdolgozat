package com.vaszily.WorkoutPlanner.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
@Setter
public class Task extends BaseEntity{
    private List<Exercise> exerciseList;
    private Integer reps;
    private Integer sets;
    private Integer usedWeight;
    private String comment;
    private Boolean done;
}
