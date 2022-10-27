package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.dto.request.WorkoutPlanRatingRequest;
import com.vaszily.WorkoutPlanner.exception.MissingAuthorityException;
import com.vaszily.WorkoutPlanner.model.Workout;
import com.vaszily.WorkoutPlanner.model.WorkoutPlan;
import com.vaszily.WorkoutPlanner.model.auth.Account;
import com.vaszily.WorkoutPlanner.repositories.WorkoutPlanRepo;
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
import java.util.stream.Collectors;

@Service
public class WorkoutPlanService implements IEntityService<WorkoutPlan> {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private final WorkoutPlanRepo workoutPlanRepo;
    private final WorkoutService workoutService;
    private final AccountService accountService;

    @Autowired
    public WorkoutPlanService(WorkoutPlanRepo workoutPlanRepo, WorkoutService workoutService, AccountService accountService) {
        this.workoutService = workoutService;
        this.workoutPlanRepo = workoutPlanRepo;
        this.accountService = accountService;
    }

    @Override
    public List<WorkoutPlan> getAll() {
        return workoutPlanRepo.findAll();
    }

    @Override
    public WorkoutPlan getById(Long Id) {
        return workoutPlanRepo.findById(Id).orElseThrow(() -> new EntityExistsException("This workoutplan does not exist! ID: "+ Id));
    }

    @Override
    @Transactional
    public WorkoutPlan save(WorkoutPlan toSave) {
        toSave.setWorkouts(getWorkoutsByDummies(toSave.getWorkouts()));
        WorkoutPlan toRet = workoutPlanRepo.saveAndFlush(toSave);
        for (Workout w : toRet.getWorkouts()) {
            w.getWorkoutPlans().add(toRet);
            workoutService.save(w);
        }
        return workoutPlanRepo.save(toSave);
    }

    @Override
    public WorkoutPlan update(Long id, WorkoutPlan workoutPlan, Principal principal) {
        WorkoutPlan toUpdate = getById(id);
        if(!toUpdate.getCreatedBy().equals(principal.getName())){
            log.info(principal.getName() + " try to update an workout plan!");
            throw new MissingAuthorityException("Can't update this workout plan! Different creator!");
        }
        toUpdate.setAccounts(workoutPlan.getAccounts());
        toUpdate.setComment(workoutPlan.getComment());
        toUpdate.setDescription(workoutPlan.getDescription());
        toUpdate.setWorkouts(workoutPlan.getWorkouts());
        toUpdate.setRating(workoutPlan.getRating());
        toUpdate.setNumberOfRating(workoutPlan.getNumberOfRating());
        toUpdate.setName(workoutPlan.getName());
        return workoutPlanRepo.save(toUpdate);
    }

    private List<Workout> getWorkoutsByDummies(List<Workout> dummies) {
        List<Workout> toRet = new ArrayList<>();
        for (Workout w : dummies) {
            toRet.add(workoutService.getById(w.getId()));
        }
        return toRet;
    }

    @Override
    @Transactional
    public void delete(Long id, Principal principal) {
        WorkoutPlan toDelete = getById(id);
        if(!toDelete.getCreatedBy().equals(principal.getName())){
            log.info(principal.getName() + " try to delete an workout plan!");
            throw new MissingAuthorityException("Can't update this workout plan! Different creator!");
        }
        toDelete.getWorkouts().forEach(workout -> {
            workout.setWorkoutPlans(workout.getWorkoutPlans()
                    .stream().filter(wp -> wp.getId()!= toDelete.getId()).collect(Collectors.toList()));
            workoutService.save(workout);
        });
        /*for(Workout workout : toDelete.getWorkouts()){
            Set<WorkoutPlan> updatedWorkoutPlanSet = new HashSet<>();
            for(WorkoutPlan workoutPlan : workout.getWorkoutPlans()){
                if(workoutPlan.getId()==toDelete.getId())
                    updatedWorkoutPlanSet.add(workoutPlan);
            }
            workout.setWorkoutPlans(updatedWorkoutPlanSet);
            workoutService.save(workout);
        }*/
        
        workoutPlanRepo.delete(toDelete);
    }
    @Transactional
    public void addWorkoutPlanToAccount(Long id, String name) {
        WorkoutPlan workoutPlan = getById(id);
        Account account = accountService.getByUserName(name);
        workoutPlan.getAccounts().add(account);
        account.getWorkoutPlans().add(workoutPlan);
        workoutPlanRepo.save(workoutPlan);
        accountService.save(account);
    }

    @Transactional
    public void removeWorkoutFromAccount(Long id, String name) {
        WorkoutPlan workoutPlan = getById(id);
        Account account = accountService.getByUserName(name);
        workoutPlan.getAccounts().remove(account);
        account.getWorkoutPlans().remove(workoutPlan);
        workoutPlanRepo.save(workoutPlan);
        accountService.save(account);
    }

    public void ratingWorkoutPlan(Long id, WorkoutPlanRatingRequest workoutPlanRatingRequest) {
        WorkoutPlan workoutPlan = getById(id);
        if(workoutPlan.getRating()==null && workoutPlan.getNumberOfRating()==null){
            workoutPlan.setRating( 1.0 * workoutPlanRatingRequest.getValue());
            workoutPlan.setNumberOfRating(1);
        }else{
            Integer newNumberOfRating = workoutPlan.getNumberOfRating()+1;
            Double newRatingValue = (1.0*workoutPlan.getRating()*workoutPlan.getNumberOfRating()+ workoutPlanRatingRequest.getValue())/newNumberOfRating;
            workoutPlan.setRating(newRatingValue);
            workoutPlan.setNumberOfRating(newNumberOfRating);
        }
    }
}
