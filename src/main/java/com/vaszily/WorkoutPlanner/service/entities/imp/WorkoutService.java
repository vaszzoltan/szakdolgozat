package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.exception.MissingAuthorityException;
import com.vaszily.WorkoutPlanner.model.Task;
import com.vaszily.WorkoutPlanner.model.Workout;
import com.vaszily.WorkoutPlanner.model.auth.Account;
import com.vaszily.WorkoutPlanner.repositories.WorkoutRepo;
import com.vaszily.WorkoutPlanner.service.entities.IEntityService;
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
public class WorkoutService implements IEntityService<Workout> {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private final WorkoutRepo workoutRepo;
    private final TaskService taskService;
    private final AccountService accountService;
    @Autowired
    public WorkoutService(WorkoutRepo workoutRepo, TaskService taskService, AccountService accountService) {
        this.taskService = taskService;
        this.workoutRepo = workoutRepo;
        this.accountService = accountService;
    }

    @Override
    public List<Workout> getAll() {
        return workoutRepo.findAll();
    }

    @Override
    public Workout getById(Long Id) {
        return workoutRepo.findById(Id).orElseThrow(() -> new EntityExistsException("This workout does not exist! ID: "+ Id));
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
    @Transactional
    public Workout save(Workout toSave, String username) {
        Account account= accountService.getByUserName(username);
        toSave.setTasks(getTasksByDummies(toSave.getTasks()));
        toSave.getAccounts().add(account);
        Workout w = workoutRepo.saveAndFlush(toSave);
        account.getWorkouts().add(w);
        accountService.save(account);
        for(Task t : w.getTasks()) {
            t.setWorkout(w);
            taskService.save(t);
        }
        return w;
    }

    @Override
    public Workout update(Long id, Workout workout, Principal principal) {
        Workout toUpdate = getById(id);
        if(!toUpdate.getCreatedBy().equals(principal.getName())){
            log.info(principal.getName() + " try to update an workout!");
            throw new MissingAuthorityException("Can't update this workout! Different creator!");
        }
        toUpdate.setName(workout.getName());
        toUpdate.setAccounts(workout.getAccounts());
        toUpdate.setAfterEffect(workout.getAfterEffect());
        toUpdate.setDescription(workout.getDescription());
        /*toUpdate.setFinishDate(workout.getFinishDate());
        toUpdate.setGoalDate(workout.getGoalDate());*/
        toUpdate.setTasks(getTasksByDummies(workout.getTasks()));
        toUpdate.setWorkoutPlans(workout.getWorkoutPlans());
        return workoutRepo.save(toUpdate);
    }

    private List<Task> getTasksByDummies(List<Task> dummies){
        List <Task> toRet = new ArrayList<>();
        for(Task t : dummies){
            toRet.add(taskService.getById(t.getId()));
        }
        return toRet;
    }

    @Override
    public void delete(Long id, Principal principal) {
        Workout toDelete = getById(id);
        if(!toDelete.getCreatedBy().equals(principal.getName())){
            log.info(principal.getName() + " try to delete an workout!");
            throw new MissingAuthorityException("Can't delete this workout! Different creator!");
        }
        for(Task t : toDelete.getTasks()){
            taskService.delete(t.getId(), principal);
        }
        workoutRepo.delete(toDelete);
    }
    @Transactional
    public void addWorkoutToAccount(Long id, String name) {
        Workout workout = getById(id);
        Account account = accountService.getByUserName(name);
        workout.getAccounts().add(account);
        account.getWorkouts().add(workout);
        workoutRepo.save(workout);
        accountService.save(account);
    }
    @Transactional
    public void removeWorkoutFromAccount(Long id, String name) {
        Workout workout = getById(id);
        Account account = accountService.getByUserName(name);
        workout.getAccounts().remove(account);
        account.getWorkouts().remove(workout);
        workoutRepo.save(workout);
        accountService.save(account);
    }
}
