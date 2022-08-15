package com.vaszily.WorkoutPlanner.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Exercise extends BaseEntity{
    private String name;
    private boolean dynamic;
    @OneToMany(mappedBy = "exercise")
    private List<ExerciseWrapper> exerciseWrappers;
}
