package com.vaszily.WorkoutPlanner.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Task extends BaseEntity{
    @OneToMany(mappedBy ="task")
    private List<Exercise> exercises;
    @ManyToOne
    private Workout workout;
    private Integer reps;
    private Integer sets;
    private Integer usedWeight;
    private String comment;
    private Boolean done;
}
