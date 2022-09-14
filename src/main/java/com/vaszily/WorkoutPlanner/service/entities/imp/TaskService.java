package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.model.Task;
import com.vaszily.WorkoutPlanner.repositories.TaskRepo;
import com.vaszily.WorkoutPlanner.service.entities.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TaskService implements EntityService<Task> {
    private final TaskRepo taskRepo;

    @Autowired
    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    public List<Task> getAll() {
        return taskRepo.findAll();
    }

    @Override
    public List<Task> getAllByName(String name) {
        return taskRepo.findAllByName(name);
    }

    @Override
    public Task getById(Long id) {
        return taskRepo.findById(id).orElseThrow(EntityExistsException::new);
    }

    @Override
    public Task save(Task toSave) {

        return taskRepo.save(toSave);
    }

    @Override
    public Task update(Long id, Task task) {
        Task toUpdate = taskRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        toUpdate.setComment(task.getComment());
        toUpdate.setDone(task.getDone());
        if(task.getExerciseWrappers()==null ||task.getExerciseWrappers().size()==0) throw new RuntimeException("Exercisewrapper cannot be null or empty!");
        toUpdate.setExerciseWrappers(task.getExerciseWrappers());
        if(task.getWorkout()==null) throw new RuntimeException("Workout cannot be null!");
        toUpdate.setWorkout(task.getWorkout());
        toUpdate.setName(task.getName());
        return taskRepo.save(toUpdate);
    }

    @Override
    public void delete(Long id) {
        Task toDelete = taskRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        taskRepo.delete(toDelete);

    }
}
