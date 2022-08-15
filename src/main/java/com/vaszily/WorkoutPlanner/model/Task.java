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
    private String name;
    @OneToMany(mappedBy ="task")
    private List<ExerciseWrapper> exerciseWrappers;
    @ManyToOne
    private Workout workout;
    private String comment;
    private Boolean done;
}
