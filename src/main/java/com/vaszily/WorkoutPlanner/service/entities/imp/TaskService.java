package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.exception.MissingAuthorityException;
import com.vaszily.WorkoutPlanner.model.ExerciseWrapper;
import com.vaszily.WorkoutPlanner.model.Task;
import com.vaszily.WorkoutPlanner.repositories.TaskRepo;
import com.vaszily.WorkoutPlanner.service.entities.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService implements EntityService<Task> {
    private Logger log = LoggerFactory.getLogger(this.getClass());
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
        Task task = taskRepo.findById(id).orElseThrow(() -> new EntityExistsException("This task does not exist! ID: "+ id));
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
    public Task update(Long id, Task task, Principal principal) {
        Task toUpdate = getById(id);
        if(!toUpdate.getCreatedBy().equals(principal.getName())){
            log.info(principal.getName() + " try to update an task!");
            throw new MissingAuthorityException("Can't update this task! Different creator!");
        }
        toUpdate.setComment(task.getComment());
        toUpdate.setDone(task.getDone());
        if(task.getExerciseWrappers()==null ||task.getExerciseWrappers().size()==0) throw new RuntimeException("Exercisewrapper cannot be null or empty!");
        toUpdate.setExerciseWrappers(getExerciseWrappersByDummies(task.getExerciseWrappers(), toUpdate));
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
    public void delete(Long id, Principal principal) {
        Task toDelete = getById(id);
        if(!toDelete.getCreatedBy().equals(principal.getName())){
            log.info(principal.getName() + " try to delete an task!");
            throw new MissingAuthorityException("Can't delete this task! Different creator!");
        }
        for(ExerciseWrapper e : toDelete.getExerciseWrappers()){
            e.setTask(null);
            exerciseWrapperService.save(e);
        }
        taskRepo.delete(toDelete);
    }
}
