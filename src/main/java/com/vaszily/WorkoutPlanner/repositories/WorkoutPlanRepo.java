package com.vaszily.WorkoutPlanner.repositories;

import com.vaszily.WorkoutPlanner.model.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutPlanRepo extends JpaRepository<WorkoutPlan,Long> {
}
