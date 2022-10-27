package com.vaszily.WorkoutPlanner.model.auth;

import com.vaszily.WorkoutPlanner.model.Workout;
import com.vaszily.WorkoutPlanner.model.WorkoutPlan;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class Account {
    @Id
    @Column(name = "USERNAME")
    private String username;
    private String password;
    private char enabled;
    @ManyToMany(mappedBy = "accounts")
    private List<WorkoutPlan> workoutPlans;
    @ManyToMany(mappedBy = "accounts")
    private List<Workout> workouts;

    public Account(String username){
        this.username = username;
    }




}
