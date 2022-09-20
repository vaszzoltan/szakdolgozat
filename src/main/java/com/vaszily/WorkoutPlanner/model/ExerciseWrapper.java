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
        if(e.getExercise()==null) throw new RuntimeException("Exercise cannot be null!");
        this.exercise = e.getExercise();
        this.sets = e.getSets();
        if(exercise.getRepeatable()!=null) {
            this.exercise.setRepeatable(e.getExercise().getRepeatable());
            if (e.getExercise().getRepeatable()) {
                this.reps = e.getReps();
                this.durationInSecond = null;
            } else {
                this.durationInSecond = e.getDurationInSecond();
                this.reps = null;
            }
        }
        this.usedWeight = e.getUsedWeight();
        this.task = task;
    }

}
