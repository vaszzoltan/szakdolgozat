package com.vaszily.WorkoutPlanner.model;

import com.vaszily.WorkoutPlanner.model.auth.Account;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class WorkoutPlan extends BaseEntity {
    private String name;
    @ManyToMany(mappedBy ="workoutPlans")
    private List<Workout> workouts;
    @ManyToMany
    private List<Account> accounts;
    private Integer rating;
    private Integer numberOfRating;
    private String description;
    private String comment;


    public WorkoutPlan(Long id) {
        this.id = id;
    }

    public void update(WorkoutPlan w){
        this.name = w.getName();
        this.workouts.addAll(w.getWorkouts());
        this.accounts.addAll(w.getAccounts());
        if(w.rating!=null){
            this.rating = (this.rating*this.numberOfRating+w.getRating())/(this.getNumberOfRating()+1);
            this.numberOfRating++;
        }
        this.description = w.getDescription();
        this.comment = w.getComment();
    }
}
