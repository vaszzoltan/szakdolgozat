package com.vaszily.WorkoutPlanner.repositories;

import com.vaszily.WorkoutPlanner.model.ExerciseWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseWrapperRepo extends JpaRepository<ExerciseWrapper, Long> {
}
