package com.vaszily.WorkoutPlanner.repositories;

import com.vaszily.WorkoutPlanner.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
}
