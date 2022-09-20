package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.model.Task;
import com.vaszily.WorkoutPlanner.model.Workout;
import com.vaszily.WorkoutPlanner.repositories.WorkoutRepo;
import com.vaszily.WorkoutPlanner.service.entities.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
@Service
public class WorkoutService implements EntityService<Workout> {
    private final WorkoutRepo workoutRepo;
    private final TaskService taskService;
    @Autowired
    public WorkoutService(WorkoutRepo workoutRepo, TaskService taskService) {
        this.taskService = taskService;
        this.workoutRepo = workoutRepo;
    }

    @Override
    public List<Workout> getAll() {
        return workoutRepo.findAll();
    }

    @Override
    public List<Workout> getAllByName(String name) {
        return workoutRepo.findAllByName(name);
    }

    @Override
    public Workout getById(Long Id) {
        return workoutRepo.findById(Id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    @Override
    public Workout save(Workout toSave) {
        toSave.setTasks(getTasksByDummies(toSave.getTasks()));
        Workout w = workoutRepo.saveAndFlush(toSave);
        for(Task t : w.getTasks()) {
            t.setWorkout(w);
            taskService.save(t);
        }
        return w;
    }

    @Override
    public Workout update(Long id, Workout workout) {
        Workout toUpdate = workoutRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        toUpdate.setName(workout.getName());
        toUpdate.setAccounts(workout.getAccounts());
        toUpdate.setAfterEffect(workout.getAfterEffect());
        toUpdate.setDescription(workout.getDescription());
        toUpdate.setFinishDate(workout.getFinishDate());
        toUpdate.setGoalDate(workout.getGoalDate());
        toUpdate.setTasks(getTasksByDummies(workout.getTasks()));
        toUpdate.setWorkoutPlans(workout.getWorkoutPlans());
        return workoutRepo.save(toUpdate);
    }
    /*private List<Account> getAccountsByDummies(List<Account> dummies){
        List<Account> toRet = new ArrayList<>();
        for(Account a : dummies){
            toRet.add(accountService.getByUserName(a.getUsername()));
        }
        return toRet;
    }*/
    private List<Task> getTasksByDummies(List<Task> dummies){
        List <Task> toRet = new ArrayList<>();
        for(Task t : dummies){
            toRet.add(taskService.getById(t.getId()));
        }
        return toRet;
    }

    @Override
    public void delete(Long id) {
        Workout toDelete = workoutRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        for(Task t : toDelete.getTasks()){
            taskService.delete(t.getId());
        }
        workoutRepo.delete(toDelete);
    }
}
