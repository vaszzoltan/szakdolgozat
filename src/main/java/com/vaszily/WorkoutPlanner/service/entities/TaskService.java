package com.vaszily.WorkoutPlanner.service.entities;

import com.vaszily.WorkoutPlanner.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements EntityService<Task> {
    @Override
    public List<Task> getAll() {
        return null;
    }

    @Override
    public List<Task> getAllByName(String name) {
        return null;
    }

    @Override
    public Task getById(Long Id) {
        return null;
    }

    @Override
    public Task save(Task toSave) {
        return null;
    }

    @Override
    public Task update(Long id, Task toUpdate) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
