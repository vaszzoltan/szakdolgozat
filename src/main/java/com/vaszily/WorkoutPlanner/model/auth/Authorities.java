package com.vaszily.WorkoutPlanner.model.auth;

import com.vaszily.WorkoutPlanner.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "authorities",
uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "authority"})})
public class Authorities extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "username", columnDefinition = "VARCHAR")
    private Account username;
    private String authority;

}
