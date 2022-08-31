package com.vaszily.WorkoutPlanner.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Exercise extends BaseEntity{
    private String name;
    private Boolean repeatable;
    @OneToMany(mappedBy = "exercise")
    private List<ExerciseWrapper> exerciseWrappers;

    public Exercise(Long id) {
        this.id = id;
    }

    public void update(Exercise exercise){
        this.name = exercise.getName();
        this.repeatable = exercise.getRepeatable();
    }
}
