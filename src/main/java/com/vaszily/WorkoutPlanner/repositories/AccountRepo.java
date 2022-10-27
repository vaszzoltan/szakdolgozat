package com.vaszily.WorkoutPlanner.repositories;

import com.vaszily.WorkoutPlanner.model.auth.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account,String> {
    Optional<Account> findByUsername(String name);
}
