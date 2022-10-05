package com.vaszily.WorkoutPlanner.model.auth;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "authorities",
uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "authority"})})
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "username", columnDefinition = "VARCHAR")
    private Account username;
    private String authority;

}
