package com.vaszily.WorkoutPlanner.repositories;

import com.vaszily.WorkoutPlanner.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
}
