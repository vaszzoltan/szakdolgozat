package com.vaszily.WorkoutPlanner.repositories;

import com.vaszily.WorkoutPlanner.model.auth.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepo extends JpaRepository<Authorities, Long> {
}
