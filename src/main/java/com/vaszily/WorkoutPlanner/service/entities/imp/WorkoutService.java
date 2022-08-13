package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.model.Workout;
import com.vaszily.WorkoutPlanner.repositories.WorkoutRepo;
import com.vaszily.WorkoutPlanner.service.entities.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
public class WorkoutService implements EntityService<Workout> {
    private final WorkoutRepo workoutRepo;
    @Autowired
    public WorkoutService(WorkoutRepo workoutRepo) {
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

    @Override
    public Workout save(Workout toSave) {
        return workoutRepo.save(toSave);
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
        toUpdate.setTasks(workout.getTasks());
        toUpdate.setWorkoutPlans(workout.getWorkoutPlans());
        return workoutRepo.save(toUpdate);
    }

    @Override
    public void delete(Long id) {
        Workout toDelete = workoutRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        workoutRepo.delete(toDelete);

    }
}
