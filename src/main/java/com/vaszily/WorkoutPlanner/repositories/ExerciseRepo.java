package com.vaszily.WorkoutPlanner.repositories;

import com.vaszily.WorkoutPlanner.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepo extends JpaRepository<Exercise, Long> {
    List<Exercise> findAllByName(String name);
}
