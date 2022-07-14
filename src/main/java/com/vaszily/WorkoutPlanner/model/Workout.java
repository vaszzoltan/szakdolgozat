package com.vaszily.WorkoutPlanner.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Workout extends BaseEntity{
    private List<Task> taskList;
    private Date goalDate;
    private Date finishDate;
    private String description;
    private String afterEffect;
}
