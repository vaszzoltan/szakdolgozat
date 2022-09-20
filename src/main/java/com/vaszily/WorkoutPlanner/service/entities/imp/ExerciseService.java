package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.exception.ReferencedEntityException;
import com.vaszily.WorkoutPlanner.model.Exercise;
import com.vaszily.WorkoutPlanner.repositories.ExerciseRepo;
import com.vaszily.WorkoutPlanner.service.entities.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ExerciseService implements EntityService<Exercise> {
    private ExerciseRepo exerciseRepo;
    @Autowired
    public ExerciseService(ExerciseRepo exerciseRepo){
        this.exerciseRepo = exerciseRepo;
    }

    @Override
    public List<Exercise> getAll() {
        return exerciseRepo.findAll();
    }

    @Override
    public List<Exercise> getAllByName(String name) {
        throw new RuntimeException();
    }

    @Override
    public Exercise getById(Long id) {
        return exerciseRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Exercise save(Exercise toSave) {
        return exerciseRepo.save(toSave);
    }

    @Override
    public Exercise update(Long id, Exercise exercise) {
        Exercise toUpdate = exerciseRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        toUpdate.update(exercise);
        return exerciseRepo.save(toUpdate);


    }

    @Override
    public void delete(Long id) {
        Exercise toDelete = exerciseRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        if(toDelete.getExerciseWrappers().size()>0)
            throw new ReferencedEntityException("This excersice used by at least a exercise wrapper!");
        exerciseRepo.delete(toDelete);
    }
}
