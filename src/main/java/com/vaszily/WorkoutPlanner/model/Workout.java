package com.vaszily.WorkoutPlanner.model;

import com.vaszily.WorkoutPlanner.model.auth.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Workout extends BaseEntity{
    private String name;
    @OneToMany(mappedBy ="workout")
    private List<Task> tasks;
    @ManyToMany
    private Set<WorkoutPlan> workoutPlans;
    @ManyToMany
    private List<Account> accounts;
    private Date goalDate;
    private Date finishDate;
    private String description;
    private String afterEffect;

    public Workout(Long id){
        this.id = id;
    }

    public void update(Workout w){
        this.name = name;
        this.tasks.addAll(w.getTasks());
        this.workoutPlans.addAll(w.getWorkoutPlans());
        this.accounts.addAll(accounts);
        this.goalDate = w.getGoalDate();
        this.finishDate = w.getFinishDate();
        this.description = w.getDescription();
        this.afterEffect = w.getAfterEffect();
    }
}
