package com.vaszily.WorkoutPlanner.repositories;

import com.vaszily.WorkoutPlanner.model.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutPlanRepo extends JpaRepository<WorkoutPlan,Long> {
}
