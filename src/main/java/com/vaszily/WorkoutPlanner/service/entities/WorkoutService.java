package com.vaszily.WorkoutPlanner.service.entities;

import com.vaszily.WorkoutPlanner.model.Workout;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WorkoutService implements EntityService<Workout> {
    @Override
    public List<Workout> getAll() {
        return null;
    }

    @Override
    public List<Workout> getAllByName(String name) {
        return null;
    }

    @Override
    public Workout getById(Long Id) {
        return null;
    }

    @Override
    public Workout save(Workout toSave) {
        return null;
    }

    @Override
    public Workout update(Long id, Workout toUpdate) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
