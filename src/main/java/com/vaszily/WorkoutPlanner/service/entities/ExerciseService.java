package com.vaszily.WorkoutPlanner.service.entities;

import com.vaszily.WorkoutPlanner.model.Exercise;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExerciseService implements EntityService<Exercise> {

    @Override
    public List<Exercise> getAll() {
        return null;
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
