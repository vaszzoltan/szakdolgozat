package com.vaszily.WorkoutPlanner.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter

public class ExerciseWrapper extends BaseEntity{
    @ManyToOne
    private Exercise exercise;
    private Integer reps;
    private Integer sets;

    private Integer durationInSecond;
    private Integer usedWeight;
    @ManyToOne
    private Task task;

}
