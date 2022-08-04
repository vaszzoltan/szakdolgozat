package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.model.Exercise;
import com.vaszily.WorkoutPlanner.repositories.ExerciseRepo;
import com.vaszily.WorkoutPlanner.service.entities.EntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class ExerciseService implements EntityService<Exercise> {
    private ExerciseRepo exerciseRepo;

    @Override
    public List<Exercise> getAll() {
        return exerciseRepo.findAll();
    }

    @Override
    public List<Exercise> getAllByName(String name) {
        return null;
    }

    @Override
    public Exercise getById(Long Id) {
        return null;
    }

    @Override
    public Exercise save(Exercise toSave) {
        exerciseRepo.save(toSave);
        return null;
    }

    @Override
    public Exercise update(Long id, Exercise toUpdate) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
