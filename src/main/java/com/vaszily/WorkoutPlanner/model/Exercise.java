package com.vaszily.WorkoutPlanner.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Exercise extends BaseEntity{
    @ManyToOne
    private Task task;
}
