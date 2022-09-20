package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.model.Workout;
import com.vaszily.WorkoutPlanner.model.WorkoutPlan;
import com.vaszily.WorkoutPlanner.repositories.WorkoutPlanRepo;
import com.vaszily.WorkoutPlanner.service.entities.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WorkoutPlanService implements EntityService<WorkoutPlan> {
    private final WorkoutPlanRepo workoutPlanRepo;
    private final WorkoutService workoutService;

    @Autowired
    public WorkoutPlanService(WorkoutPlanRepo workoutPlanRepo, WorkoutService workoutService) {
        this.workoutService = workoutService;
        this.workoutPlanRepo = workoutPlanRepo;
    }

    @Override
    public List<WorkoutPlan> getAll() {
        return workoutPlanRepo.findAll();
    }

    @Override
    public List<WorkoutPlan> getAllByName(String name) {
        return workoutPlanRepo.findAllByName(name);
    }

    @Override
    public WorkoutPlan getById(Long Id) {
        return workoutPlanRepo.findById(Id).orElseThrow(EntityNotFoundException::new);
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
    public WorkoutPlan update(Long id, WorkoutPlan workoutPlan) {
        WorkoutPlan toUpdate = workoutPlanRepo.findById(id).orElseThrow(EntityNotFoundException::new);
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
    public void delete(Long id) {
        WorkoutPlan toDelete = workoutPlanRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        for(Workout w : toDelete.getWorkouts()){
            Long deleteId = toDelete.getId();
            w.setWorkoutPlans(w.getWorkoutPlans().stream().filter(a -> a.getId()!=deleteId).collect(Collectors.toSet()));
            workoutService.save(w);
        }
        workoutPlanRepo.delete(toDelete);
    }
}
