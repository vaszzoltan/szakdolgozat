package com.vaszily.WorkoutPlanner.repositories;

import com.vaszily.WorkoutPlanner.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findAllByName(String name);
}
