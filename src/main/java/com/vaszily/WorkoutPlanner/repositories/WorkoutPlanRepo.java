package com.vaszily.WorkoutPlanner.repositories;

import com.vaszily.WorkoutPlanner.model.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutPlanRepo extends JpaRepository<WorkoutPlan,Long> {
    List<WorkoutPlan> findAllByName(String name);
}
