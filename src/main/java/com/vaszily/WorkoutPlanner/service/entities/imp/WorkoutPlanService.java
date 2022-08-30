package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.model.WorkoutPlan;
import com.vaszily.WorkoutPlanner.repositories.WorkoutPlanRepo;
import com.vaszily.WorkoutPlanner.service.entities.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
public class WorkoutPlanService implements EntityService<WorkoutPlan> {
    private final WorkoutPlanRepo workoutPlanRepo;

    @Autowired
    public WorkoutPlanService(WorkoutPlanRepo workoutPlanRepo) {
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
    public WorkoutPlan save(WorkoutPlan toSave) {
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

    @Override
    public void delete(Long id) {
        WorkoutPlan toDelete = workoutPlanRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        workoutPlanRepo.delete(toDelete);
    }
}
