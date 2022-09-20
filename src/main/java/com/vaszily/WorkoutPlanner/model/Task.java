package com.vaszily.WorkoutPlanner.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task extends BaseEntity implements Serializable {
    private String name;
    @OneToMany(mappedBy ="task", fetch = FetchType.EAGER)
    private List<ExerciseWrapper> exerciseWrappers;
    @ManyToOne
    private Workout workout;
    private String comment;
    private Boolean done;

    public Task(Long id){
        this.id = id;
    }

    public void  update(Task t){
        this.name = t.getName();
        this.exerciseWrappers.addAll(t.exerciseWrappers);
        this.workout = t.getWorkout();
        this.comment = t.getComment();
        this.done = t.getDone();
    }
}
