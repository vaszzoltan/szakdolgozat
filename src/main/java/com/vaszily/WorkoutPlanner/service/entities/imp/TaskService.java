package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.model.ExerciseWrapper;
import com.vaszily.WorkoutPlanner.model.Task;
import com.vaszily.WorkoutPlanner.repositories.TaskRepo;
import com.vaszily.WorkoutPlanner.service.entities.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService implements EntityService<Task> {
    private final TaskRepo taskRepo;
    private final ExerciseWrapperService exerciseWrapperService;

    @Autowired
    public TaskService(TaskRepo taskRepo, ExerciseWrapperService exerciseWrapperService) {
        this.taskRepo = taskRepo;
        this.exerciseWrapperService = exerciseWrapperService;
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
        Task task = taskRepo.findById(id).orElseThrow(EntityExistsException::new);
        return task;
    }

    @Override
    @Transactional
    public Task save(Task toSave) {
        toSave.setExerciseWrappers(getExerciseWrappersByDummies(toSave.getExerciseWrappers(), toSave));
        Task toRet = taskRepo.saveAndFlush(toSave);
        for(ExerciseWrapper e : toSave.getExerciseWrappers()) {
            e.setTask(toSave);
            exerciseWrapperService.save(e);
        }
        return toRet;
    }

    @Override
    public Task update(Long id, Task task) {
        Task toUpdate = taskRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        toUpdate.setComment(task.getComment());
        toUpdate.setDone(task.getDone());
        if(task.getExerciseWrappers()==null ||task.getExerciseWrappers().size()==0) throw new RuntimeException("Exercisewrapper cannot be null or empty!");
        toUpdate.setExerciseWrappers(getExerciseWrappersByDummies(task.getExerciseWrappers(), toUpdate));
        //if(task.getWorkout()==null) throw new RuntimeException("Workout cannot be null!");
        toUpdate.setWorkout(task.getWorkout());
        toUpdate.setName(task.getName());
        return taskRepo.saveAndFlush(toUpdate);
    }

    private List<ExerciseWrapper> getExerciseWrappersByDummies(List<ExerciseWrapper> dummies, Task task){
        List<ExerciseWrapper> toRet = new ArrayList<>();
        for(ExerciseWrapper e : dummies){
            e.setTask(task);
            toRet.add(exerciseWrapperService.getById(e.getId()));
        }
        return toRet;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Task toDelete = taskRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        for(ExerciseWrapper e : toDelete.getExerciseWrappers()){
            e.setTask(null);
            exerciseWrapperService.save(e);
        }
        taskRepo.delete(toDelete);
    }
}
