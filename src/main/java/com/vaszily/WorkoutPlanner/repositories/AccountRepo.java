package com.vaszily.WorkoutPlanner.repositories;

import com.vaszily.WorkoutPlanner.model.auth.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    List<Account> findAllByUsername(String name);
}
