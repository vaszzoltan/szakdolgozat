package com.vaszily.WorkoutPlanner.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ExerciseWrapper extends BaseEntity{
    @ManyToOne
    private Exercise exercise;
    private Integer reps;
    private Integer sets;
    private Integer durationInSecond;
    private Integer usedWeight;
    @ManyToOne
    private Task task;

    public ExerciseWrapper(Long id){
        this.id = id;
    }

    public void update(ExerciseWrapper e){
        this.exercise = e.getExercise();
        this.reps = e.getReps();
        this.sets = e.getSets();
        this.durationInSecond = e.getDurationInSecond();
        this.usedWeight = e.getUsedWeight();
        this.task = task;
    }

}
